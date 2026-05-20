package com.example.nobelpublic.di

import com.example.nobelpublic.data.remote.NobelApiService
import com.example.nobelpublic.data.repository.NobelRepositoryImpl
import com.example.nobelpublic.domain.repository.NobelRepository
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
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
    }

    @Provides
    @Singleton
    fun provideApiService(client: HttpClient) = NobelApiService(client)

    @Provides
    @Singleton
    fun provideRepository(apiService: NobelApiService): NobelRepository = NobelRepositoryImpl(apiService)
}
