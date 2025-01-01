package com.github.beaver010.beechat

import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitTask

class Task(val execute: () -> Unit) {
    private var bukkitTask: BukkitTask? = null

    fun runTimer(period: Long, delay: Long = 0) {
        stop()

        bukkitTask = Bukkit.getScheduler().runTaskTimer(
            BeeChat.instance,
            execute,
            delay,
            period
        )
    }

    fun stop() {
        bukkitTask?.cancel()
    }
}