plugins {
    kotlin("jvm") version "2.0.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "com.github.beaver010"
version = "1.2.1"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
    compileOnly("io.github.miniplaceholders:miniplaceholders-api:2.2.3")
    compileOnly("me.clip:placeholderapi:2.11.6")
    implementation("org.spongepowered:configurate-yaml:4.1.2")
    implementation("org.spongepowered:configurate-extra-kotlin:4.1.2")
}

kotlin {
    jvmToolchain(17)
}