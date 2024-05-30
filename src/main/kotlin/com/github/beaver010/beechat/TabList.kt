package com.github.beaver010.beechat

import com.github.beaver010.beechat.integration.MiniPlaceholdersIntegration
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import org.bukkit.entity.Player

object TabList {
    fun send(player: Player) {
        val miniMessage = MiniMessage.miniMessage()
        val config = BeeChat.instance.config
        val audiencePlaceholders = MiniPlaceholdersIntegration.audiencePlaceholders(player)

        if (config.tabList.playerName.isNotEmpty()) {
            val tabListName = miniMessage.deserialize(
                config.tabList.playerName,
                TagResolver.resolver(
                    Placeholders.name(player),
                    audiencePlaceholders
                )
            )

            player.playerListName(tabListName)
        }

        val placeholders = TagResolver.resolver(
            MiniPlaceholdersIntegration.globalPlaceholders(),
            audiencePlaceholders
        )

        player.sendPlayerListHeaderAndFooter(
            miniMessage.deserialize(config.tabList.header, placeholders),
            miniMessage.deserialize(config.tabList.footer, placeholders)
        )
    }

    fun update() {
        BeeChat.instance
            .server
            .onlinePlayers
            .forEach(TabList::send)
    }
}