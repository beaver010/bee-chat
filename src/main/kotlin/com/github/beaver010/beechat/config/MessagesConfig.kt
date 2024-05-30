package com.github.beaver010.beechat.config

import org.spongepowered.configurate.objectmapping.ConfigSerializable

@ConfigSerializable
data class MessagesConfig(
    val reload: String = "<green>The <gradient:yellow:gold>BeeChat</gradient> configuration has been reloaded"
)
