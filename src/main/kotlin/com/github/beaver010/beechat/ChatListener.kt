package com.github.beaver010.beechat

import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

object ChatListener: Listener {
    @EventHandler
    fun onChat(event: AsyncChatEvent) {
        val format = BeeChat.pluginConfig.messageFormat

        if (format.isNotEmpty()) {
            event.renderer { source, _, message, _ ->
                val tags = TagResolver.resolver(
                    Placeholders.name(source),
                    Placeholders.message(source, message),
                    BeeChat.miniPlaceholders.audiencePlaceholders(source)
                )

                MiniMessage.miniMessage().deserialize(format, tags)
            }
        }
    }
}