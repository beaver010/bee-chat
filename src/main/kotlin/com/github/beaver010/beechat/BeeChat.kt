package com.github.beaver010.beechat

import com.github.beaver010.beechat.listener.ChatListener
import com.github.beaver010.beechat.listener.JoinListener
import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIBukkitConfig
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitTask

class BeeChat : JavaPlugin() {
    override fun onLoad() {
        CommandAPI.onLoad(CommandAPIBukkitConfig(this).silentLogs(true))

        instance = this
    }

    override fun onEnable() {
        saveDefaultConfig()
        pluginConfig = PluginConfig(config)

        CommandAPI.onEnable()
        Command.register()

        Permissions.register()
        registerEvents(ChatListener)

        if (pluginConfig.tabListFormattingEnabled) {
            registerEvents(JoinListener)

            if (pluginConfig.tabListUpdatePeriod > 0) {
                restartTabListUpdateTask()
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
        lateinit var instance: BeeChat private set
        lateinit var pluginConfig: PluginConfig private set
        private var tabListUpdateTask: BukkitTask? = null

        fun reloadConfig() {
            instance.run {
                reloadConfig()
                pluginConfig = PluginConfig(config)
            }
        }

        fun restartTabListUpdateTask() {
            tabListUpdateTask?.cancel()
            tabListUpdateTask = instance.server
                .scheduler
                .runTaskTimer(
                    instance,
                    TabList::update,
                    0,
                    pluginConfig.tabListUpdatePeriod
                )
        }
    }
}