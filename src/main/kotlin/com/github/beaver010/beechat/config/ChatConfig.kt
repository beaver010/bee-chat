package com.github.beaver010.beechat.config

import kotlinx.serialization.Serializable

@Serializable
data class ChatConfig(
    val messageFormat: String = "<name> <yellow>→ <gray><message>",
    val channels: List<ChatChannelConfig> = emptyList(),
    val spy: SpyConfig = SpyConfig(),
)
