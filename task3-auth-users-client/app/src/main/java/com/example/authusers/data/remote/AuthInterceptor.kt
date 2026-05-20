package com.example.authusers.data.remote

import com.example.authusers.data.local.TokenStorage
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenStorage: TokenStorage
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking { tokenStorage.token.first() }
        val request = chain.request().newBuilder().apply {
            token?.takeIf { it.isNotBlank() }?.let {
                addHeader("Authorization", "Bearer $it")
            }
        }.build()
        return chain.proceed(request)
    }
}
