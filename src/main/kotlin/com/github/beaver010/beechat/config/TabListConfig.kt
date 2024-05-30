package com.github.beaver010.beechat.config

import org.spongepowered.configurate.objectmapping.ConfigSerializable

@ConfigSerializable
data class TabListConfig(
    val enable: Boolean = true,
    val updatePeriod: Long = 200,
    val header: String = "<br><gradient:yellow:gold>Example Server<br>",
    val footer: String = "<br><yellow>Store: <gray>store.example.com<br>",
    val playerName: String = "<name>"
)
