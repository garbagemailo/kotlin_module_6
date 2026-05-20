plugins {
    kotlin("jvm") version "2.2.21"
    kotlin("plugin.serialization") version "2.2.21"
    application
}

group = "com.example"
version = "1.0.0"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}


dependencies {
    implementation("io.ktor:ktor-server-core-jvm:2.3.12")
    implementation("io.ktor:ktor-server-netty-jvm:2.3.12")
    implementation("io.ktor:ktor-server-config-yaml-jvm:2.3.12")
    implementation("io.ktor:ktor-server-auth-jvm:2.3.12")
    implementation("io.ktor:ktor-server-auth-jwt-jvm:2.3.12")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:2.3.12")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:2.3.12")
    implementation("io.ktor:ktor-server-call-logging-jvm:2.3.12")
    implementation("io.ktor:ktor-server-status-pages-jvm:2.3.12")
    implementation("ch.qos.logback:logback-classic:1.5.6")
    implementation("com.auth0:java-jwt:4.4.0")
    testImplementation("io.ktor:ktor-server-tests-jvm:2.3.12")
    testImplementation(kotlin("test"))
}
