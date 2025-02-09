package com.github.beaver010.beechat

import com.charleskorn.kaml.Yaml
import com.charleskorn.kaml.YamlConfiguration
import com.charleskorn.kaml.YamlNamingStrategy
import com.charleskorn.kaml.decodeFromStream
import com.charleskorn.kaml.encodeToStream
import com.github.beaver010.beechat.config.Config
import com.github.beaver010.beechat.extensions.register
import com.github.beaver010.beechat.listener.ChatListener
import com.github.beaver010.beechat.listener.JoinListener
import com.github.beaver010.beechat.listener.QuitListener
import kotlinx.serialization.SerializationException
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class BeeChat : JavaPlugin() {
    lateinit var config: Config private set

    private val tabListUpdateTask = Task(execute = TabList::update)

    init {
        instance = this
    }

    override fun onEnable() {
        reload()

        Permissions.register()
        BeeChatCommand.register()

        ChatListener.register(this)
        JoinListener.register(this)
        QuitListener.register(this)
    }

    fun reload() {
        config = loadConfig()
        if (shouldRestartTabListTask()) {
            tabListUpdateTask.runTimer(period = config.tabList.updatePeriod)
        }
    }

    private fun shouldRestartTabListTask(): Boolean =
        config.tabList.enable && config.tabList.updatePeriod > 0

    private fun loadConfig(): Config {
        val yaml = createYaml()
        val configFile = File(dataFolder, "config.yml")

        return if (configFile.exists()) {
            configFile.inputStream().use {
                try {
                    yaml.decodeFromStream(it)
                } catch (e: SerializationException) {
                    logger.severe("Failed to load config.yml: ${e.localizedMessage}")
                    logger.warning("Using the default configuration due to a previous error")
                    Config()
                }
            }
        } else {
            configFile.outputStream().use { stream ->
                Config().also {
                    try {
                        yaml.encodeToStream(it, stream)
                    } catch (e: SerializationException) {
                        logger.severe("Failed to save config.yml: ${e.localizedMessage}")
                    }
                }
            }
        }
    }

    private fun createYaml(): Yaml =
        Yaml(
            configuration = YamlConfiguration(
                breakScalarsAt = 120,
                yamlNamingStrategy = YamlNamingStrategy.KebabCase,
            )
        )

    companion object {
        lateinit var instance: BeeChat private set
    }
}
