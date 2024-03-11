package com.github.beaver010.beechat

import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import org.bukkit.entity.Player

object TabList {
    fun send(player: Player) {
        val miniMessage = MiniMessage.miniMessage()
        val miniPlaceholders = BeeChat.miniPlaceholders

        val audiencePlaceholders = miniPlaceholders.audiencePlaceholders(player)

        if (PluginConfig.tabListPlayerName.isNotEmpty()) {
            val tabListName = miniMessage.deserialize(
                PluginConfig.tabListPlayerName,
                TagResolver.resolver(
                    Placeholders.name(player),
                    audiencePlaceholders
                )
            )

            player.playerListName(tabListName)
        }

        val placeholders = TagResolver.resolver(
            miniPlaceholders.globalPlaceholders(),
            audiencePlaceholders
        )

        player.sendPlayerListHeaderAndFooter(
            miniMessage.deserialize(PluginConfig.tabListHeader, placeholders),
            miniMessage.deserialize(PluginConfig.tabListFooter, placeholders)
        )
    }

    fun update() {
        BeeChat.instance
            .server
            .onlinePlayers
            .forEach(TabList::send)
    }
}