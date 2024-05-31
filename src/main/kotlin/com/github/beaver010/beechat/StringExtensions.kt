package com.github.beaver010.beechat

import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver

fun String.miniMessage(tagResolver: TagResolver = TagResolver.empty()) =
    MiniMessage.miniMessage().deserialize(this, tagResolver)
