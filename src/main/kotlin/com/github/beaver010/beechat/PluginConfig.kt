package com.github.beaver010.beechat

object PluginConfig {
    val messageFormat: String

    val tabListFormattingEnabled: Boolean
    val tabListUpdatePeriod: Long
    val tabListHeader: String
    val tabListFooter: String
    val tabListPlayerName: String

    init {
        BeeChat.instance.config.run {
            addDefault("tab-list.enable", false)

            messageFormat = getString("chat.message-format") ?: ""

            tabListFormattingEnabled = getBoolean("tab-list.enable")
            tabListUpdatePeriod = getLong("tab-list.update-period")
            tabListHeader = getString("tab-list.header") ?: ""
            tabListFooter = getString("tab-list.footer") ?: ""
            tabListPlayerName = getString("tab-list.player-name") ?: ""
        }
    }
}