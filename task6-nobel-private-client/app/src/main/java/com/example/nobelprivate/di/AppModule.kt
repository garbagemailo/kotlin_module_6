package com.example.nobelprivate.di

import com.example.nobelprivate.data.remote.PrivateNobelApi
import com.example.nobelprivate.data.repository.PrivateNobelRepositoryImpl
import com.example.nobelprivate.domain.repository.PrivateNobelRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    @Provides
    @Singleton
    fun provideApi(client: HttpClient) = PrivateNobelApi(client)

    @Provides
    @Singleton
    fun provideRepository(api: PrivateNobelApi): PrivateNobelRepository = PrivateNobelRepositoryImpl(api)
}
