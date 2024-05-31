package com.github.beaver010.beechat.integration

import com.github.beaver010.beechat.BeeChat

open class PluginIntegration(pluginName: String) {
    val isEnabled = BeeChat.instance
        .server
        .pluginManager
        .isPluginEnabled(pluginName)
}
