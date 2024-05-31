package com.github.beaver010.beechat

import com.github.beaver010.beechat.integration.MiniPlaceholdersIntegration
import com.github.beaver010.beechat.integration.PlaceholderAPIIntegration
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import org.bukkit.entity.Player

object TabList {
    fun send(player: Player) {
        val tabListConfig = BeeChat.instance.config.tabList
        val audiencePlaceholders = MiniPlaceholdersIntegration.audiencePlaceholders(player)

        if (tabListConfig.playerName.isNotEmpty()) {
            var tabListName = tabListConfig.playerName

            tabListName = PlaceholderAPIIntegration.parsePlaceholders(player, tabListName)

            val tags = TagResolver.resolver(Placeholders.name(player), audiencePlaceholders)
            player.playerListName(tabListName.miniMessage(tags))
        }

        var tabListHeader = tabListConfig.header
        var tabListFooter = tabListConfig.footer

        tabListHeader = PlaceholderAPIIntegration.parsePlaceholders(player, tabListHeader)
        tabListFooter = PlaceholderAPIIntegration.parsePlaceholders(player, tabListFooter)

        val placeholders = TagResolver.resolver(
            MiniPlaceholdersIntegration.globalPlaceholders(),
            audiencePlaceholders
        )

        player.sendPlayerListHeaderAndFooter(
            tabListHeader.miniMessage(placeholders),
            tabListFooter.miniMessage(placeholders)
        )
    }

    fun update() {
        BeeChat.instance
            .server
            .onlinePlayers
            .forEach(TabList::send)
    }
}