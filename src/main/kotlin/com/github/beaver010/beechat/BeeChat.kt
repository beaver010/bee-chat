package com.github.beaver010.beechat

import com.github.beaver010.beechat.listener.ChatListener
import com.github.beaver010.beechat.listener.JoinListener
import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIBukkitConfig
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class BeeChat : JavaPlugin() {
    override fun onLoad() {
        CommandAPI.onLoad(CommandAPIBukkitConfig(this).silentLogs(true))

        instance = this
    }

    override fun onEnable() {
        CommandAPI.onEnable()

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

    override fun onDisable() {
        CommandAPI.onDisable()
    }

    private fun registerEvents(listener: Listener) {
        server.pluginManager.registerEvents(listener, this)
    }

    companion object {
        lateinit var instance: BeeChat
    }
}