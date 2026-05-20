package com.example.nobelpostgres

import com.example.nobelpostgres.data.db.DatabaseFactory
import com.example.nobelpostgres.data.remote.NobelRemoteDataSource
import com.example.nobelpostgres.data.repository.AuthRepositoryImpl
import com.example.nobelpostgres.data.repository.PrizeRepositoryImpl
import com.example.nobelpostgres.domain.AuthService
import com.example.nobelpostgres.domain.LoginUseCase
import com.example.nobelpostgres.domain.SyncPrizesUseCase
import com.example.nobelpostgres.plugins.configureRouting
import com.example.nobelpostgres.plugins.configureSecurity
import com.example.nobelpostgres.plugins.configureSerialization
import com.example.nobelpostgres.plugins.configureStatusPages
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.callloging.CallLogging

fun Application.module() {
    DatabaseFactory.init()

    val prizeRepository = PrizeRepositoryImpl()
    val authRepository = AuthRepositoryImpl()
    val remoteDataSource = NobelRemoteDataSource()
    val authService = AuthService.fromEnvironment()

    DatabaseFactory.seedDefaultUsers(authRepository)
    prizeRepository.ensureSampleDataIfEmpty()

    install(CallLogging)
    configureSerialization()
    configureStatusPages()
    configureSecurity(authService)
    configureRouting(
        authService = authService,
        loginUseCase = LoginUseCase(authRepository, authService),
        prizeRepository = prizeRepository,
        syncPrizesUseCase = SyncPrizesUseCase(prizeRepository, remoteDataSource)
    )
}
