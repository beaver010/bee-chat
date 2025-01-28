package com.github.beaver010.beechat.integration

import org.bukkit.Bukkit

interface PluginIntegration {
    val pluginName: String
    val isAvailable: Boolean
        get() = Bukkit.getPluginManager().isPluginEnabled(pluginName)
}
