package com.example.authusers.data.repository

import com.example.authusers.data.local.TokenStorage
import com.example.authusers.data.remote.AuthApi
import com.example.authusers.data.remote.LoginRequestDto
import com.example.authusers.domain.model.AuthSession
import com.example.authusers.domain.model.User
import com.example.authusers.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi,
    private val tokenStorage: TokenStorage
) : AuthRepository {

    override val tokenFlow: Flow<String?> = tokenStorage.token

    override suspend fun login(username: String, password: String): AuthSession {
        val response = api.login(LoginRequestDto(username, password))
        val token = response.accessToken ?: response.token ?: error("Токен не пришёл в ответе")
        tokenStorage.saveToken(token)
        return AuthSession(
            token = token,
            fullName = "${response.firstName} ${response.lastName}",
            email = response.email,
            image = response.image
        )
    }

    override suspend fun getUsers(): List<User> = api.getUsers().users.map { it.toDomain() }

    override suspend fun getUser(id: Int): User = api.getUser(id).toDomain()

    override suspend fun logout() {
        tokenStorage.clearToken()
    }

    private fun com.example.authusers.data.remote.UserDto.toDomain() = User(
        id = id,
        firstName = firstName,
        lastName = lastName,
        username = username,
        email = email,
        image = image
    )
}
