package com.github.beaver010.beechat

import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class BeeChat: JavaPlugin() {
    override fun onEnable() {
        saveDefaultConfig()
        pluginConfig = PluginConfig(config)

        registerEvents(ChatListener)
    }

    private fun registerEvents(listener: Listener) {
        server.pluginManager.registerEvents(listener, this)
    }

    companion object {
        lateinit var pluginConfig: PluginConfig
    }
}