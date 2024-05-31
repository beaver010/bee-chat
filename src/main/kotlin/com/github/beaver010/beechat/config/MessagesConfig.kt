package com.github.beaver010.beechat.config

import org.spongepowered.configurate.objectmapping.ConfigSerializable

@ConfigSerializable
data class MessagesConfig(
    val unknownSubcommand: String = "<red>Unknown subcommand",
    val reload: String = "<green>The <gradient:yellow:gold>BeeChat</gradient> configuration has been reloaded"
)
