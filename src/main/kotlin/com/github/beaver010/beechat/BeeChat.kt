package com.github.beaver010.beechat

import com.github.beaver010.beechat.listener.ChatListener
import com.github.beaver010.beechat.listener.JoinListener
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class BeeChat: JavaPlugin() {
    override fun onLoad() {
        instance = this
    }

    override fun onEnable() {
        saveDefaultConfig()

        Permissions.register()

        registerEvents(ChatListener)

        if (PluginConfig.tabListFormattingEnabled) {
            registerEvents(JoinListener)

            if (PluginConfig.tabListUpdatePeriod > 0) {
                server.scheduler.runTaskTimer(
                    this,
                    TabList::update,
                    0,
                    PluginConfig.tabListUpdatePeriod
                )
            }
        }
    }

    private fun registerEvents(listener: Listener) {
        server.pluginManager.registerEvents(listener, this)
    }

    companion object {
        lateinit var instance: BeeChat
    }
}