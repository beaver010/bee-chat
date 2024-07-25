package com.github.beaver010.beechat.extensions

import com.github.beaver010.beechat.Keys
import com.github.beaver010.beechat.Permissions
import org.bukkit.entity.Player
import org.bukkit.persistence.PersistentDataType

var Player.spyModeEnabled: Boolean
    get() = hasPermission(Permissions.spy) && persistentDataContainer
        .getOrDefault(Keys.spyModeEnabled, PersistentDataType.BOOLEAN, false)
    set(value) = persistentDataContainer
        .set(Keys.spyModeEnabled, PersistentDataType.BOOLEAN, value)
