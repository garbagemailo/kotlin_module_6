package com.example.nobelpostgres.domain

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.auth.jwt.JWTPrincipal
import java.util.Date

class AuthService(
    val issuer: String,
    val audience: String,
    private val secret: String
) {
    private val algorithm = Algorithm.HMAC256(secret)

    fun verifier() = JWT.require(algorithm)
        .withIssuer(issuer)
        .withAudience(audience)
        .build()

    fun createToken(user: DbUser): String {
        val expiresAt = Date(System.currentTimeMillis() + 30 * 60 * 1000)
        return JWT.create()
            .withIssuer(issuer)
            .withAudience(audience)
            .withClaim("userId", user.id)
            .withClaim("username", user.username)
            .withClaim("role", user.role)
            .withExpiresAt(expiresAt)
            .sign(algorithm)
    }

    fun userId(principal: JWTPrincipal?): Int? = principal?.payload?.getClaim("userId")?.asInt()

    companion object {
        fun fromEnvironment() = AuthService(
            issuer = System.getenv("JWT_ISSUER") ?: "nobel-postgres-server",
            audience = System.getenv("JWT_AUDIENCE") ?: "nobel-postgres-client",
            secret = System.getenv("JWT_SECRET") ?: "super_secret_key_for_nobel_postgres_server_123456"
        )
    }
}
