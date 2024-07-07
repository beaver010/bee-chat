package com.github.beaver010.beechat.integration

import org.bukkit.Bukkit

open class PluginIntegration(pluginName: String) {
    val isAvailable = Bukkit.getPluginManager().isPluginEnabled(pluginName)
}
