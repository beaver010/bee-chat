package com.github.beaver010.beechat.listener

import com.github.beaver010.beechat.BeeChat
import com.github.beaver010.beechat.Placeholders
import com.github.beaver010.beechat.integration.MiniPlaceholdersIntegration
import com.github.beaver010.beechat.miniMessage
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

object ChatListener : Listener {
    @EventHandler
    fun onChat(event: AsyncChatEvent) {
        val format = BeeChat.instance.config.chat.messageFormat

        if (format.isEmpty()) {
            return
        }

        event.renderer { source, _, message, _ ->
            val tags = TagResolver.resolver(
                Placeholders.name(source),
                Placeholders.message(source, message),
                MiniPlaceholdersIntegration.audiencePlaceholders(source)
            )

            format.miniMessage(tags)
        }
    }
}