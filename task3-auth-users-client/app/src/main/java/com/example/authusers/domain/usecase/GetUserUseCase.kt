package com.example.authusers.domain.usecase

import com.example.authusers.domain.repository.AuthRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(id: Int) = repository.getUser(id)
}
