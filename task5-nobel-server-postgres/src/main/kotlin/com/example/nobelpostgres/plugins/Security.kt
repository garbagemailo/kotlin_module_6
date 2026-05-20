package com.example.nobelpostgres.plugins

import com.example.nobelpostgres.domain.AuthService
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.jwt.jwt

fun Application.configureSecurity(authService: AuthService) {
    install(Authentication) {
        jwt("auth-jwt") {
            realm = "nobel-postgres-api"
            verifier(authService.verifier())
            validate { credential ->
                if (credential.payload.audience.contains(authService.audience)) JWTPrincipal(credential.payload) else null
            }
        }
    }
}
