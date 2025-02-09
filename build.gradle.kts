plugins {
    kotlin("jvm") version "2.1.10"
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
    val configurateVersion by properties

    compileOnly("io.papermc.paper:paper-api:$paperVersion")
    compileOnly("io.github.miniplaceholders:miniplaceholders-api:$miniplaceholdersVersion")
    compileOnly("me.clip:placeholderapi:$placeholderapiVersion")
    implementation("org.spongepowered:configurate-yaml:$configurateVersion")
    implementation("org.spongepowered:configurate-extra-kotlin:$configurateVersion")
}

kotlin {
    jvmToolchain(17)
}