package com.github.beaver010.beechat.listener

import com.github.beaver010.beechat.BeeChat
import com.github.beaver010.beechat.Placeholders
import com.github.beaver010.beechat.integration.MiniPlaceholdersIntegration
import com.github.beaver010.beechat.integration.PlaceholderAPIIntegration
import com.github.beaver010.beechat.miniMessage
import io.papermc.paper.chat.ChatRenderer
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
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

        val channel = config.channels
            .find { channel ->
                if (channel.permission.isNotEmpty() && !sender.hasPermission(channel.permission)) {
                    return@find false
                }

                message.startsWith(channel.identifier)
            }

        if (channel != null) {
            message = message.removePrefix(channel.identifier)

            event.viewers().removeAll { viewer ->
                !channel.canSee(sender, viewer)
            }
        }

        event.renderer(ChatRenderer.viewerUnaware { source, sourceDisplayName, _ ->
            val tags = TagResolver.resolver(
                Placeholders.name(sourceDisplayName),
                Placeholders.message(source, message),
                MiniPlaceholdersIntegration.audiencePlaceholders(source)
            )

            val formattedMessage = format.miniMessage(tags)

            if (channel == null) {
                return@viewerUnaware formattedMessage
            }

            channel.format.miniMessage(
                TagResolver.resolver(
                    tags,
                    Placeholders.formattedMessage(formattedMessage)
                )
            )
        })
    }
}
