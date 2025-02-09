package com.github.beaver010.beechat

import com.github.beaver010.beechat.config.Config
import com.github.beaver010.beechat.extensions.register
import com.github.beaver010.beechat.listener.ChatListener
import com.github.beaver010.beechat.listener.JoinListener
import com.github.beaver010.beechat.listener.QuitListener
import org.bukkit.plugin.java.JavaPlugin
import org.spongepowered.configurate.kotlin.extensions.get
import org.spongepowered.configurate.kotlin.objectMapperFactory
import org.spongepowered.configurate.yaml.NodeStyle
import org.spongepowered.configurate.yaml.YamlConfigurationLoader
import java.io.File

class BeeChat : JavaPlugin() {
    lateinit var config: Config private set

    private val tabListUpdateTask = Task(execute = TabList::update)

    override fun onLoad() {
        instance = this
    }

    override fun onEnable() {
        loadConfig()

        Permissions.register()
        BeeChatCommand.register()

        ChatListener.register(this)
        JoinListener.register(this)
        QuitListener.register(this)

        if (config.tabList.enable && config.tabList.updatePeriod > 0) {
            restartTabListUpdateTask()
        }
    }

    fun loadConfig() {
        val configFile = File(dataFolder, "config.yml")
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

        config = rootNode.get() ?: Config()
    }

    fun restartTabListUpdateTask() {
        tabListUpdateTask.runTimer(period = config.tabList.updatePeriod)
    }

    companion object {
        lateinit var instance: BeeChat private set
    }
}
