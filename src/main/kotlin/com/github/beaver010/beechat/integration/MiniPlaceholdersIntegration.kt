package com.github.beaver010.beechat.integration

import io.github.miniplaceholders.api.MiniPlaceholders
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver

class MiniPlaceholdersIntegration: PluginIntegration("MiniPlaceholders") {
    fun audiencePlaceholders(audience: Audience) = if (isEnabled) {
        MiniPlaceholders.getAudiencePlaceholders(audience)
    } else {
        TagResolver.empty()
    }

    fun globalPlaceholders() = if (isEnabled) {
        MiniPlaceholders.getGlobalPlaceholders()
    } else {
        TagResolver.empty()
    }
}