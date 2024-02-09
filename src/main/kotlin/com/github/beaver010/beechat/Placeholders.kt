package com.github.beaver010.beechat

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import org.bukkit.entity.Player

object Placeholders {
    private const val NAME = "name"
    private const val MESSAGE = "message"

    fun name(player: Player): TagResolver.Single =
        Placeholder.unparsed(NAME, player.name)

    fun message(chatMessage: Component): TagResolver.Single =
        Placeholder.component(MESSAGE, chatMessage)
}