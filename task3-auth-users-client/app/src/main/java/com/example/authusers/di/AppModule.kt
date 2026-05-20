package com.example.authusers.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.example.authusers.data.local.TokenStorage
import com.example.authusers.data.remote.AuthApi
import com.example.authusers.data.remote.AuthInterceptor
import com.example.authusers.data.repository.AuthRepositoryImpl
import com.example.authusers.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "auth_prefs")

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTokenStorage(@ApplicationContext context: Context): TokenStorage = TokenStorage(context.dataStore)

    @Provides
    @Singleton
    fun provideInterceptor(tokenStorage: TokenStorage): AuthInterceptor = AuthInterceptor(tokenStorage)

    @Provides
    @Singleton
    fun provideOkHttp(interceptor: AuthInterceptor): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        .build()

    @Provides
    @Singleton
    fun provideApi(client: OkHttpClient): AuthApi = Retrofit.Builder()
        .baseUrl("https://dummyjson.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(AuthApi::class.java)

    @Provides
    @Singleton
    fun provideRepository(api: AuthApi, tokenStorage: TokenStorage): AuthRepository = AuthRepositoryImpl(api, tokenStorage)
}
