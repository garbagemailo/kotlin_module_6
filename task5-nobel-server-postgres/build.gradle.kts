import org.gradle.api.GradleException
import org.gradle.api.tasks.JavaExec

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
    implementation("io.ktor:ktor-server-call-logging-jvm:2.3.12")
    implementation("io.ktor:ktor-server-status-pages-jvm:2.3.12")
    implementation("io.ktor:ktor-server-html-builder-jvm:2.3.12")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:2.3.12")
    implementation("io.ktor:ktor-client-core-jvm:2.3.12")
    implementation("io.ktor:ktor-client-cio-jvm:2.3.12")
    implementation("io.ktor:ktor-client-content-negotiation-jvm:2.3.12")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:2.3.12")
    implementation("org.jetbrains.exposed:exposed-core:0.53.0")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.53.0")
    implementation("org.jetbrains.exposed:exposed-java-time:0.53.0")
    implementation("com.zaxxer:HikariCP:5.1.0")
    implementation("org.postgresql:postgresql:42.7.3")
    implementation("com.auth0:java-jwt:4.4.0")
    implementation("org.mindrot:jbcrypt:0.4")
    implementation("ch.qos.logback:logback-classic:1.5.6")
    testImplementation(kotlin("test"))
}


fun runLocalCommand(vararg command: String): Int =
    ProcessBuilder(*command)
        .directory(projectDir)
        .inheritIO()
        .start()
        .waitFor()

tasks.register("ensureLocalPostgres") {
    group = "application"
    description = "Starts the local PostgreSQL service from docker-compose.yml when DATABASE_URL is not set."

    doLast {
        if (!System.getenv("DATABASE_URL").isNullOrBlank()) {
            println("DATABASE_URL is set; skipping local Docker PostgreSQL startup.")
            return@doLast
        }

        try {
            val composeExit = runLocalCommand("docker", "compose", "up", "-d", "postgres")
            if (composeExit != 0) {
                throw GradleException("Docker Compose could not start PostgreSQL. Run 'docker compose up -d postgres' manually, or set DATABASE_URL/DATABASE_USER/DATABASE_PASSWORD for an existing PostgreSQL database.")
            }

            repeat(30) { attempt ->
                val readyExit = runLocalCommand("docker", "compose", "exec", "-T", "postgres", "pg_isready", "-U", "postgres", "-d", "nobel")
                if (readyExit == 0) {
                    println("Local PostgreSQL is ready.")
                    return@doLast
                }
                println("Waiting for local PostgreSQL (${attempt + 1}/30)...")
                Thread.sleep(1000)
            }

            throw GradleException("Local PostgreSQL container started, but did not become ready. Check it with 'docker compose logs postgres'.")
        } catch (error: java.io.IOException) {
            throw GradleException("PostgreSQL is required for task5. Install Docker and run 'docker compose up -d postgres', or set DATABASE_URL/DATABASE_USER/DATABASE_PASSWORD for an existing PostgreSQL database.", error)
        }
    }
}

tasks.named<JavaExec>("run") {
    dependsOn("ensureLocalPostgres")
}
