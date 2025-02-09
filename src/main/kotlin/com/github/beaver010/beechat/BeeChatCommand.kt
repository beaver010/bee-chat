package com.github.beaver010.beechat

import com.github.beaver010.beechat.extensions.spyModeEnabled
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.PluginIdentifiableCommand
import org.bukkit.entity.Player

object BeeChatCommand : Command("beechat"), PluginIdentifiableCommand {
    override fun execute(
        sender: CommandSender,
        commandLabel: String,
        args: Array<out String>,
    ): Boolean {
        val messages = plugin.config.messages

        return when (args.firstOrNull()) {
            "reload" -> {
                if (!sender.hasPermission(Permissions.reload)) {
                    sender.sendMessage(Bukkit.permissionMessage())
                    return false
                }

                plugin.reload()
                sender.sendRichMessage(messages.reload)
                true
            }
            "spy" -> {
                if (sender !is Player) {
                    sender.sendRichMessage(messages.notPlayer)
                    return false
                }

                if (!sender.hasPermission(Permissions.spy)) {
                    sender.sendMessage(Bukkit.permissionMessage())
                    return false
                }

                if (sender.spyModeEnabled) {
                    sender.spyModeEnabled = false
                    sender.sendRichMessage(messages.spyModeDisabled)
                } else {
                    sender.spyModeEnabled = true
                    sender.sendRichMessage(messages.spyModeEnabled)
                }

                true
            }
            else -> {
                sender.sendRichMessage(messages.unknownSubcommand)
                false
            }
        }
    }

    override fun tabComplete(
        sender: CommandSender,
        alias: String,
        args: Array<out String>,
    ): MutableList<String> {
        val completions = mutableListOf<String>()
        if (args.size == 1) {
            if (sender.hasPermission(Permissions.reload)) {
                completions.add("reload")
            }
            if (sender.hasPermission(Permissions.spy) && sender is Player) {
                completions.add("spy")
            }
        }

        return completions
    }

    override fun getPlugin(): BeeChat {
        return BeeChat.instance
    }
}
