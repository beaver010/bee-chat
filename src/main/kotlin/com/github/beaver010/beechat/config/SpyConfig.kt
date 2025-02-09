package com.github.beaver010.beechat.config

import kotlinx.serialization.Serializable

@Serializable
data class SpyConfig(
    val format: String = "<aqua>Spy</aqua> <channel_message>",
    val disableOnLeave: Boolean = true,
)
