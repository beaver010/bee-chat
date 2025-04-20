package com.github.beaver010.beechat.utils

import com.github.beaver010.beechat.BeeChat
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.logger.slf4j.ComponentLogger
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

object UpdateChecker {
    private const val LATEST_VERSION_URL = "https://hangar.papermc.io/api/v1/projects/BeeChat/latest?channel=Release"

    fun checkForUpdates(logger: ComponentLogger) {
        logger.info(Message.CHECKING_FOR_UPDATES)

        val latestVersion = latestVersion()
        if (latestVersion == null) {
            logger.warn(Message.FAILED_TO_CHECK_FOR_UPDATES)
            return
        }

        @Suppress("UnstableApiUsage")
        val currentVersion = BeeChat.instance.pluginMeta.version

        if (latestVersion == currentVersion) {
            logger.info(Message.NO_NEW_VERSION_AVAILABLE)
        } else {
            logger.info(Message.newVersionAvailable(currentVersion, latestVersion))
            logger.info(Message.hangarLink)
            logger.info(Message.modrinthLink)
            logger.info(Message.githubLink)
        }
    }

    private fun latestVersion(): String? {
        val client = HttpClient.newHttpClient()
        val uri = URI(LATEST_VERSION_URL)

        val request = HttpRequest.newBuilder()
            .uri(uri)
            .GET()
            .header("accept", "text/plain")
            .build()

        val response = try {
            client.send(request, HttpResponse.BodyHandlers.ofString())
        } catch (_: Exception) {
            return null
        }

        return if (response.statusCode() == 200) {
            response.body()
        } else {
            null
        }
    }

    private object Message {
        const val CHECKING_FOR_UPDATES = "Checking for updates..."
        const val FAILED_TO_CHECK_FOR_UPDATES = "Failed to check for updates"
        const val NO_NEW_VERSION_AVAILABLE = "You are running the latest version!"

        val hangarLink get() = Component.empty()
            .append(text("Hangar", NamedTextColor.BLUE).decorate(TextDecoration.BOLD))
            .append(text(": https://hangar.papermc.io/beaver010/BeeChat"))

        val modrinthLink get() = Component.empty()
            .append(text("Modrinth", NamedTextColor.DARK_GREEN).decorate(TextDecoration.BOLD))
            .append(text(": https://modrinth.com/plugin/beechat"))

        val githubLink get() = Component.empty()
            .append(text("GitHub", NamedTextColor.DARK_GRAY).decorate(TextDecoration.BOLD))
            .append(text(": https://github.com/beaver010/bee-chat/releases"))

        fun newVersionAvailable(currentVersion: String, latestVersion: String) =
            text("A new version of BeeChat is available!")
                .decorate(TextDecoration.BOLD)
                .appendSpace()
                .append(text('('))
                .append(text(currentVersion, NamedTextColor.GOLD))
                .append(text(" -> "))
                .append(text(latestVersion, NamedTextColor.DARK_GREEN))
                .append(text(')'))
                .appendSpace()
                .append(text("Download it from:"))
    }
}
