package com.example.nobelmemory.plugins

import com.example.nobelmemory.domain.AuthService
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.jwt.jwt

fun Application.configureSecurity(authService: AuthService) {
    install(Authentication) {
        jwt("auth-jwt") {
            realm = authService.realm
            verifier(authService.verifier())
            validate { credential ->
                if (credential.payload.audience.contains(authService.audience)) {
                    io.ktor.server.auth.jwt.JWTPrincipal(credential.payload)
                } else null
            }
        }
    }
}
