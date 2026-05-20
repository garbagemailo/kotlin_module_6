package com.example.nobelmemory

import com.example.nobelmemory.data.InMemoryPrizeRepository
import com.example.nobelmemory.data.SeedData
import com.example.nobelmemory.domain.AuthService
import com.example.nobelmemory.domain.GetLaureatesUseCase
import com.example.nobelmemory.domain.GetPrizeByKeyUseCase
import com.example.nobelmemory.domain.GetPrizesUseCase
import com.example.nobelmemory.plugins.configureRouting
import com.example.nobelmemory.plugins.configureSecurity
import com.example.nobelmemory.plugins.configureSerialization
import com.example.nobelmemory.plugins.configureStatusPages
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.callloging.CallLogging

fun Application.module() {
    val repository = InMemoryPrizeRepository(SeedData.prizes)
    val authService = AuthService(
        issuer = environment.config.property("jwt.issuer").getString(),
        audience = environment.config.property("jwt.audience").getString(),
        secret = environment.config.property("jwt.secret").getString(),
        realm = environment.config.property("jwt.realm").getString()
    )

    install(CallLogging)
    configureSerialization()
    configureStatusPages()
    configureSecurity(authService)
    configureRouting(
        authService = authService,
        getPrizesUseCase = GetPrizesUseCase(repository),
        getPrizeByKeyUseCase = GetPrizeByKeyUseCase(repository),
        getLaureatesUseCase = GetLaureatesUseCase(repository)
    )
}
