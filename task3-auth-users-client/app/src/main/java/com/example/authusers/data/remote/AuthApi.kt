package com.example.authusers.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthApi {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequestDto): LoginResponseDto

    @GET("users")
    suspend fun getUsers(): UsersResponseDto

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int): UserDto
}
