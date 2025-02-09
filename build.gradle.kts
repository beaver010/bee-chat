plugins {
    kotlin("jvm") version "2.1.10"
    kotlin("plugin.serialization") version "2.1.10"
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
}

kotlin {
    jvmToolchain(21)
}