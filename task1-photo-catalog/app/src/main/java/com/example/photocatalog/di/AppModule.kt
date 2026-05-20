package com.example.photocatalog.di

import com.example.photocatalog.data.remote.PicsumApi
import com.example.photocatalog.data.repository.PhotoRepositoryImpl
import com.example.photocatalog.domain.repository.PhotoRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        .build()

    @Provides
    @Singleton
    fun providePicsumApi(client: OkHttpClient): PicsumApi = Retrofit.Builder()
        .baseUrl("https://picsum.photos/")
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .client(client)
        .build()
        .create(PicsumApi::class.java)

    @Provides
    @Singleton
    fun providePhotoRepository(api: PicsumApi): PhotoRepository = PhotoRepositoryImpl(api)
}
