package com.github.beaver010.beechat.config

import org.spongepowered.configurate.objectmapping.ConfigSerializable

@ConfigSerializable
data class ChatConfig(
    val messageFormat: String = "<name> <yellow>→ <gray><message>",
    val channels: List<ChatChannelConfig> = emptyList(),
    val spy: SpyConfig = SpyConfig()
)
