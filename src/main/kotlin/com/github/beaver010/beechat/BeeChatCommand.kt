package com.github.beaver010.beechat

import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.PluginIdentifiableCommand
import org.bukkit.plugin.Plugin

object BeeChatCommand : Command("beechat"), PluginIdentifiableCommand {
    init {
        this.permission = Permissions.reload.name
    }

    override fun execute(
        sender: CommandSender,
        commandLabel: String,
        args: Array<out String>
    ): Boolean {
        testPermission(sender)

        if (args.firstOrNull() == "reload") {
            this.plugin.loadConfig()
            this.plugin.restartTabListUpdateTask()
            val message = this.plugin.config.messages.reload
            sender.sendMessage(MiniMessage.miniMessage().deserialize(message))
        }

        return true
    }

    override fun tabComplete(
        sender: CommandSender,
        alias: String,
        args: Array<out String>
    ): MutableList<String> {
        val completions = mutableListOf<String>()
        if (args.size == 1) {
            completions.add("reload")
        }
        return completions
    }

    override fun getPlugin(): BeeChat {
        return BeeChat.instance
    }
}