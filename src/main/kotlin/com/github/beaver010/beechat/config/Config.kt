package com.github.beaver010.beechat.config

import org.spongepowered.configurate.objectmapping.ConfigSerializable

@ConfigSerializable
data class Config(
    val chat: ChatConfig = ChatConfig(),
    val tabList: TabListConfig = TabListConfig(),
    val messages: MessagesConfig = MessagesConfig(),
)
