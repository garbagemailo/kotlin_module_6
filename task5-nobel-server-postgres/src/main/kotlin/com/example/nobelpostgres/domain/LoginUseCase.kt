package com.example.nobelpostgres.domain

import com.example.nobelpostgres.data.repository.AuthRepositoryImpl
import org.mindrot.jbcrypt.BCrypt

class LoginUseCase(
    private val authRepository: AuthRepositoryImpl,
    private val authService: AuthService
) {
    operator fun invoke(request: LoginRequest): LoginResponse? {
        val user = authRepository.findByUsername(request.username) ?: return null
        if (!BCrypt.checkpw(request.password, user.passwordHash)) return null
        return LoginResponse(token = authService.createToken(user))
    }
}
