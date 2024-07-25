package com.github.beaver010.beechat.listener

import com.github.beaver010.beechat.BeeChat
import com.github.beaver010.beechat.Placeholders
import com.github.beaver010.beechat.config.ChatChannelConfig
import com.github.beaver010.beechat.extensions.miniMessage
import com.github.beaver010.beechat.extensions.spyModeEnabled
import com.github.beaver010.beechat.integration.MiniPlaceholdersIntegration
import com.github.beaver010.beechat.integration.PlaceholderAPIIntegration
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

object ChatListener : Listener {
    @EventHandler
    fun onChat(event: AsyncChatEvent) {
        val config = BeeChat.instance.config.chat
        val sender = event.player
        val format = PlaceholderAPIIntegration.parsePlaceholders(sender, config.messageFormat)
        if (format.isEmpty()) return

        var message = event.signedMessage().message()
        val channel = findChannel(sender, message, config.channels)

        channel?.let {
            message = message.removePrefix(it.identifier)
            filterViewers(sender, it, event.viewers())
        }

        event.renderer { source, sourceDisplayName, _, viewer ->
            val baseTags = TagResolver.resolver(
                Placeholders.name(sourceDisplayName),
                Placeholders.message(source, message),
                MiniPlaceholdersIntegration.audiencePlaceholders(source)
            )

            val formattedMessage = format.miniMessage(baseTags)
            if (channel == null) return@renderer formattedMessage

            val channelTags = TagResolver.resolver(baseTags, Placeholders.formattedMessage(formattedMessage))
            val channelMessage = channel.format.miniMessage(channelTags)

            val shouldApplySpyFormatting = viewer is Player && !channel.canSee(sender, viewer)
            if (!shouldApplySpyFormatting) return@renderer channelMessage

            val spyTags = TagResolver.resolver(channelTags, Placeholders.channelMessage(channelMessage))
            config.spy.format.miniMessage(spyTags)
        }
    }

    private fun findChannel(sender: Player, message: String, channels: List<ChatChannelConfig>) =
        channels.find { channel ->
            (channel.permission.isEmpty() || sender.hasPermission(channel.permission)) &&
                    message.startsWith(channel.identifier)
        }

    private fun filterViewers(sender: Player, channel: ChatChannelConfig, viewers: MutableSet<Audience>) {
        viewers.removeAll { viewer ->
            viewer is Player && !channel.canSee(sender, viewer) && !viewer.spyModeEnabled
        }
    }
}
