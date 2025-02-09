package com.github.beaver010.beechat.config

import kotlinx.serialization.Serializable

@Serializable
data class MessagesConfig(
    val unknownSubcommand: String = "<red>Unknown subcommand",
    val reload: String = "<green>The <gradient:yellow:gold>BeeChat</gradient> configuration has been reloaded",
    val notPlayer: String = "<red>This command can only be executed by a player",
    val spyModeEnabled: String = "<aqua>Spy</aqua> mode <green>enabled</green>. Enter the command again to <red>disable</red>",
    val spyModeDisabled: String = "<aqua>Spy</aqua> mode <red>disabled</red>",
)
