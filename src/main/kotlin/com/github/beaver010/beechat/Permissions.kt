package com.github.beaver010.beechat

import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.permissions.Permission

object Permissions {
    val reload = pluginPermission("reload")
    val spy = pluginPermission("spy")

    private val allMessageFormatting = formatPermission("all")
    private val messageFormattingPermissions = mapOf(
        formatPermission("color") to TagResolver.resolver(
            StandardTags.color(),
            StandardTags.gradient(),
            StandardTags.rainbow()
        ),
        formatPermission("reset") to StandardTags.reset(),
        formatPermission("decorations") to StandardTags.decorations(),
        formatPermission("insertion") to StandardTags.insertion(),
        formatPermission("click") to StandardTags.clickEvent(),
        formatPermission("hover") to StandardTags.hoverEvent()
    )

    fun register() {
        val pm = Bukkit.getPluginManager()

        pm.addPermission(reload)
        pm.addPermission(spy)
        pm.addPermission(allMessageFormatting)
        messageFormattingPermissions.keys.forEach(pm::addPermission)
    }

    fun allowedMiniMessageTags(player: Player): TagResolver {
        if (player.hasPermission(allMessageFormatting)) {
            return TagResolver.standard()
        }

        val tags = messageFormattingPermissions
            .filter { player.hasPermission(it.key) }
            .values

        return TagResolver.resolver(tags)
    }

    private fun formatPermission(name: String) =
        pluginPermission("format.$name")

    private fun pluginPermission(name: String) =
        Permission("beechat.$name")
}
