package com.github.beaver010.beechat

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.entity.Player

object Placeholders {
    private val emptyMiniMessage = MiniMessage.builder()
        .tags(TagResolver.empty())
        .build()

    fun name(player: Player): TagResolver.Single =
        Placeholder.unparsed("name", player.name)

    fun message(player: Player, chatMessage: Component): TagResolver.Single {
        val messagePlainText = PlainTextComponentSerializer
            .plainText()
            .serialize(chatMessage)

        val allowedTags = Permissions.allowedMiniMessageTags(player)
        val message = this.emptyMiniMessage.deserialize(messagePlainText, allowedTags)

        return Placeholder.component("message", message)
    }
}
