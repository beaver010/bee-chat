package com.github.beaver010.beechat

import com.github.beaver010.beechat.integration.MiniPlaceholdersIntegration
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class BeeChat: JavaPlugin() {
    override fun onLoad() {
        instance = this
    }

    override fun onEnable() {
        saveDefaultConfig()
        pluginConfig = PluginConfig(config)

        Permissions.register()

        miniPlaceholders = MiniPlaceholdersIntegration()

        registerEvents(ChatListener)
    }

    private fun registerEvents(listener: Listener) {
        server.pluginManager.registerEvents(listener, this)
    }

    companion object {
        lateinit var instance: BeeChat
        lateinit var pluginConfig: PluginConfig
        lateinit var miniPlaceholders: MiniPlaceholdersIntegration
    }
}