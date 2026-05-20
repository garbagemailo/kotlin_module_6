package com.example.authusers.domain.usecase

import com.example.authusers.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(username: String, password: String) = repository.login(username, password)
}
