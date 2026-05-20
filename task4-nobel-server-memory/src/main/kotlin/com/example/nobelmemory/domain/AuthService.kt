package com.example.nobelmemory.domain

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.auth.jwt.JWTPrincipal
import java.util.Date

class AuthService(
    val issuer: String,
    val audience: String,
    private val secret: String,
    val realm: String
) {
    private val algorithm = Algorithm.HMAC256(secret)

    fun verifier() = JWT.require(algorithm)
        .withIssuer(issuer)
        .withAudience(audience)
        .build()

    fun login(username: String, password: String): LoginResponse? {
        if (username != "admin" || password != "admin123") return null
        val expiresAt = Date(System.currentTimeMillis() + 30 * 60 * 1000)
        val token = JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim("username", username)
            .withExpiresAt(expiresAt)
            .sign(algorithm)
        return LoginResponse(token = token)
    }

    fun username(principal: JWTPrincipal?): String? = principal?.payload?.getClaim("username")?.asString()
}
