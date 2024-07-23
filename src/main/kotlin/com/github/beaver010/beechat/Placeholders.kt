package com.github.beaver010.beechat

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import org.bukkit.entity.Player

object Placeholders {
    private val emptyMiniMessage = MiniMessage.builder()
        .tags(TagResolver.empty())
        .build()

    fun name(playerName: Component): TagResolver =
        Placeholder.component("name", playerName)

    fun message(player: Player, chatMessage: String): TagResolver {
        val allowedTags = Permissions.allowedMiniMessageTags(player)
        val message = emptyMiniMessage.deserialize(chatMessage, allowedTags)

        return Placeholder.component("message", message)
    }

    fun formattedMessage(message: Component): TagResolver =
        Placeholder.component("message_format", message)
}
