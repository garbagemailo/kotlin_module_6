package com.example.authusers.domain.repository

import com.example.authusers.domain.model.AuthSession
import com.example.authusers.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val tokenFlow: Flow<String?>
    suspend fun login(username: String, password: String): AuthSession
    suspend fun getUsers(): List<User>
    suspend fun getUser(id: Int): User
    suspend fun logout()
}
