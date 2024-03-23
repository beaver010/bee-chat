package com.github.beaver010.beechat.listener

import com.github.beaver010.beechat.TabList
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

object JoinListener : Listener {
    @EventHandler
    fun onJoin(joinEvent: PlayerJoinEvent) {
        TabList.send(joinEvent.player)
    }
}