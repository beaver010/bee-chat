package com.github.beaver010.beechat.config

import net.kyori.adventure.audience.Audience
import org.bukkit.entity.Player
import org.spongepowered.configurate.objectmapping.ConfigSerializable

@ConfigSerializable
data class ChatChannelConfig(
    val identifier: String = "",
    val distance: Double = 0.0,
    val permission: String = "",
    val format: String = "<message_format>"
) {
    fun canSee(sender: Player, viewer: Audience): Boolean {
        if (viewer !is Player) {
            return true
        }

        var hasPermission = true
        if (permission.isNotEmpty()) {
            hasPermission = viewer.hasPermission(permission)
        }

        var isAccessible = true
        if (distance > 0) {
            val location = sender.location
            isAccessible = location.distance(viewer.location) <= distance
        }

        return hasPermission && isAccessible
    }
}
