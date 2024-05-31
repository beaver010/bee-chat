package com.github.beaver010.beechat

import com.github.beaver010.beechat.config.Config
import com.github.beaver010.beechat.listener.ChatListener
import com.github.beaver010.beechat.listener.JoinListener
import org.bukkit.command.Command
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitTask
import org.spongepowered.configurate.kotlin.extensions.get
import org.spongepowered.configurate.kotlin.objectMapperFactory
import org.spongepowered.configurate.yaml.NodeStyle
import org.spongepowered.configurate.yaml.YamlConfigurationLoader
import java.io.File

class BeeChat : JavaPlugin() {
    lateinit var config: Config private set

    private var tabListUpdateTask: BukkitTask? = null

    override fun onLoad() {
        instance = this
    }

    override fun onEnable() {
        loadConfig()

        Permissions.register()
        registerCommand(BeeChatCommand)
        registerEvents(ChatListener)

        if (this.config.tabList.enable) {
            registerEvents(JoinListener)

            if (this.config.tabList.updatePeriod > 0) {
                restartTabListUpdateTask()
            }
        }
    }

    private fun registerCommand(command: Command) {
        this.server.commandMap.register(this.name, command)
    }

    private fun registerEvents(listener: Listener) {
        this.server.pluginManager.registerEvents(listener, this)
    }

    fun loadConfig() {
        val configFile = File(this.dataFolder, "config.yml")
        saveDefaultConfig()

        val loader = YamlConfigurationLoader.builder()
            .file(configFile)
            .nodeStyle(NodeStyle.BLOCK)
            .defaultOptions { opts ->
                opts.serializers { builder ->
                    builder.registerAnnotatedObjects(objectMapperFactory())
                }
            }
            .build()

        val rootNode = loader.load()

        this.config = rootNode.get() ?: Config()
    }

    fun restartTabListUpdateTask() {
        this.tabListUpdateTask?.cancel()
        this.tabListUpdateTask = this.server.scheduler.runTaskTimer(
            instance,
            TabList::update,
            0,
            config.tabList.updatePeriod
        )
    }

    companion object {
        lateinit var instance: BeeChat private set
    }
}