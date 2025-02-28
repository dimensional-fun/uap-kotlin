plugins {
    groovy
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

val kotlinVersion = "2.0.0"
dependencies {
    implementation(kotlin("gradle-plugin", version = kotlinVersion))
    implementation(kotlin("serialization", version = kotlinVersion))
    implementation("org.jetbrains.kotlinx:atomicfu-gradle-plugin:0.24.0")

    // code gen
    implementation("dev.kord.codegen:kotlinpoet:main-SNAPSHOT")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.7.1")
    implementation("net.mamoe.yamlkt:yamlkt:0.13.0")

    // groovy shit
    implementation(gradleApi())
    implementation(localGroovy())
}
