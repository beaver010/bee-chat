package com.github.beaver010.beechat.integration

import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.entity.Player

object PlaceholderAPIIntegration : PluginIntegration("PlaceholderAPI") {
    fun parsePlaceholders(player: Player, text: String) = if (isEnabled) {
        PlaceholderAPI.setPlaceholders(player, text)
    } else {
        text
    }
}