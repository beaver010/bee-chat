package com.github.beaver010.beechat.extensions

import org.bukkit.Bukkit
import org.bukkit.command.Command

fun Command.register(fallbackPrefix: String = "beechat") {
    Bukkit.getCommandMap().register(fallbackPrefix, this)
}