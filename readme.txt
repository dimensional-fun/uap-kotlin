uap-kotlin
---

A multi-platform kotlin implementation of ua-parser [1]. This specific implementation uses the shared regex patterns and overrides from regexes.yaml [2].

However, we use code-gen to avoid runtime YAML parsing and I/O at the cost of a bigger artifact, but you can use your own regexes via kotlinx.serialization!

[1] https://github.com/ua-parser
[2] https://github.com/ua-parser/uap-core/blob/master/regexes.yaml

gradle installation
---

repositories {
    maven("https://maven.dimensional.fun/releases")
}

// JVM-only artifact
dependencies {
    implementation("gay.vzt:uap-kotlin-jvm:1.0")
}

// or gradle-specific common artifact
kotlin.sourceSets["commonMain"].dependencies {
    implementation("gay.vzt:uap-kotlin:1.0")
}
