package com.github.beaver010.beechat.extensions

import org.bukkit.Bukkit
import org.bukkit.command.Command

fun Command.register() {
    this.register(Bukkit.getCommandMap())
}