package com.github.beaver010.beechat

import org.bukkit.configuration.file.FileConfiguration

class PluginConfig(config: FileConfiguration) {
    val messageFormat = config.getString("chat.message-format") ?: ""
}