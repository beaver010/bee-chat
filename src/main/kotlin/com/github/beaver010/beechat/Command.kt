package com.github.beaver010.beechat

import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.literalArgument
import net.kyori.adventure.text.minimessage.MiniMessage

object Command {
    fun register() {
        commandAPICommand("beechat") {
            literalArgument("reload")
            withPermission("beechat.reload")
            anyExecutor { sender, _ ->
                BeeChat.reloadConfig()
                BeeChat.restartTabListUpdateTask()
                val message = BeeChat.pluginConfig.reloadMessage
                sender.sendMessage(
                    MiniMessage.miniMessage().deserialize(message)
                )
            }
        }
    }
}