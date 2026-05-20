package com.example.authusers.domain.usecase

import com.example.authusers.domain.repository.AuthRepository
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke() = repository.tokenFlow
}
