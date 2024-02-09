package com.github.beaver010.beechat.integration

import io.github.miniplaceholders.api.MiniPlaceholders
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver

class MiniPlaceholdersIntegration: PluginIntegration("MiniPlaceholders") {
    fun audiencePlaceholders(audience: Audience): TagResolver {
        return if (isEnabled) {
            MiniPlaceholders.getAudiencePlaceholders(audience)
        } else {
            TagResolver.empty()
        }
    }
}