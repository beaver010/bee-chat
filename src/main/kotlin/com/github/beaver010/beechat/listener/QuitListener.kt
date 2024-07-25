package com.github.beaver010.beechat.listener

import com.github.beaver010.beechat.BeeChat
import com.github.beaver010.beechat.extensions.spyModeEnabled
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

object QuitListener : Listener {
    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        val config = BeeChat.instance.config.chat
        if (config.spy.disableOnLeave) {
            val player = event.player
            if (player.spyModeEnabled) {
                player.spyModeEnabled = false
            }
        }
    }
}