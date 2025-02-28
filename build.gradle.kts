plugins {
    `maven-publish`

    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

group = "gay.vzt"
version = "1.2"

repositories {
    mavenCentral()
}

kotlin {
    explicitApi()

    jvm()
    jvmToolchain(8)

    js {
        nodejs()
        browser()
    }

    wasmJs {
        nodejs()
        browser()
    }

    wasmWasi {
        nodejs()
    }

    linuxX64()
    linuxArm64()

    mingwX64()

    sourceSets["commonMain"].dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.7.1")
    }
}

publishing {
    repositories {
        maven("https://maven.dimensional.fun/releases") {
            credentials.username = System.getenv("REPO_ALIAS")
            credentials.password = System.getenv("REPO_TOKEN")
        }
    }
}
