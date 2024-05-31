package com.github.beaver010.beechat

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.PluginIdentifiableCommand

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
        val messages = this.plugin.config.messages

        if (args.firstOrNull() == "reload") {
            this.plugin.loadConfig()
            this.plugin.restartTabListUpdateTask()

            sender.sendMessage(messages.reload.miniMessage())

            return true
        }

        sender.sendMessage(messages.unknownSubcommand.miniMessage())

        return false
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
