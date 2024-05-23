package com.github.beaver010.beechat

import com.github.beaver010.beechat.listener.ChatListener
import com.github.beaver010.beechat.listener.JoinListener
import org.bukkit.command.Command
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitTask

class BeeChat : JavaPlugin() {
    override fun onLoad() {
        instance = this
    }

    override fun onEnable() {
        saveDefaultConfig()
        pluginConfig = PluginConfig(config)

        Permissions.register()
        registerCommand(BeeChatCommand)
        registerEvents(ChatListener)

        if (pluginConfig.tabListFormattingEnabled) {
            registerEvents(JoinListener)

            if (pluginConfig.tabListUpdatePeriod > 0) {
                restartTabListUpdateTask()
            }
        }
    }

    private fun registerCommand(command: Command) {
        server.commandMap.register(name, command)
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