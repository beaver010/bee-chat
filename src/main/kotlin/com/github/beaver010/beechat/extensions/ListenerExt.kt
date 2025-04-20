package com.github.beaver010.beechat.extensions

import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin

fun Listener.register(plugin: Plugin) {
    Bukkit.getPluginManager().registerEvents(this, plugin)
}