package com.github.beaver010.beechat

import com.github.beaver010.beechat.integration.MiniPlaceholdersIntegration
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import org.bukkit.entity.Player

object TabList {
    fun send(player: Player) {
        val config = BeeChat.instance.config
        val audiencePlaceholders = MiniPlaceholdersIntegration.audiencePlaceholders(player)

        if (config.tabList.playerName.isNotEmpty()) {
            val tags = TagResolver.resolver(Placeholders.name(player), audiencePlaceholders)
            val tabListName = config.tabList.playerName.miniMessage(tags)
            player.playerListName(tabListName)
        }

        val placeholders = TagResolver.resolver(
            MiniPlaceholdersIntegration.globalPlaceholders(),
            audiencePlaceholders
        )

        player.sendPlayerListHeaderAndFooter(
            config.tabList.header.miniMessage(placeholders),
            config.tabList.footer.miniMessage(placeholders)
        )
    }

    fun update() {
        BeeChat.instance
            .server
            .onlinePlayers
            .forEach(TabList::send)
    }
}