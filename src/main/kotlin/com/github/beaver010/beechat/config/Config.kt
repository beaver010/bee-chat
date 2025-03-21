package com.github.beaver010.beechat.config

import kotlinx.serialization.Serializable

@Serializable
data class Config(
    val checkForUpdates: Boolean = true,
    val chat: ChatConfig = ChatConfig(),
    val tabList: TabListConfig = TabListConfig(),
    val messages: MessagesConfig = MessagesConfig(),
)
