plugins {
    kotlin("jvm") version "2.1.20"
    kotlin("plugin.serialization") version "2.1.20"
    id("com.gradleup.shadow") version "8.3.6"
}

group = properties["group"] as String
version = properties["version"] as String

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
}

dependencies {
    val paperVersion by properties
    val miniplaceholdersVersion by properties
    val placeholderapiVersion by properties
    val kamlVersion by properties

    compileOnly("io.papermc.paper:paper-api:$paperVersion")
    compileOnly("io.github.miniplaceholders:miniplaceholders-api:$miniplaceholdersVersion")
    compileOnly("me.clip:placeholderapi:$placeholderapiVersion")
    implementation("com.charleskorn.kaml:kaml:$kamlVersion")
    implementation("org.bstats:bstats-bukkit:3.0.2")
}

tasks {
    build {
        dependsOn(shadowJar)
    }

    shadowJar {
        relocate("org.bstats", "com.github.beaver010.beechat.metrics")
    }

    processResources {
        val props = mapOf("version" to version)
        inputs.properties(props)
        filteringCharset = "UTF-8"
        filesMatching("paper-plugin.yml") {
            expand(props)
        }
    }
}

kotlin {
    jvmToolchain(21)
}
