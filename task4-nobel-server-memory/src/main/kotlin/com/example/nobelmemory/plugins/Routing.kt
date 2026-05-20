package com.example.nobelmemory.plugins

import com.example.nobelmemory.domain.AuthService
import com.example.nobelmemory.domain.GetLaureatesUseCase
import com.example.nobelmemory.domain.GetPrizeByKeyUseCase
import com.example.nobelmemory.domain.GetPrizesUseCase
import com.example.nobelmemory.domain.LoginRequest
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

fun Application.configureRouting(
    authService: AuthService,
    getPrizesUseCase: GetPrizesUseCase,
    getPrizeByKeyUseCase: GetPrizeByKeyUseCase,
    getLaureatesUseCase: GetLaureatesUseCase
) {
    routing {
        get("/") {
            call.respond(mapOf("service" to "Nobel Prize API (memory)", "status" to "ok"))
        }

        post("/auth/login") {
            val request = call.receive<LoginRequest>()
            val response = authService.login(request.username, request.password)
            if (response == null) {
                call.respond(HttpStatusCode.Unauthorized, mapOf("message" to "Invalid credentials"))
            } else {
                call.respond(response)
            }
        }

        authenticate("auth-jwt") {
            route("/prizes") {
                get {
                    call.respond(getPrizesUseCase())
                }

                get("/{year}/{category}") {
                    val year = call.parameters["year"]?.toIntOrNull()
                    val category = call.parameters["category"]
                    if (year == null || category == null) {
                        call.respond(HttpStatusCode.BadRequest, mapOf("message" to "Invalid year/category"))
                        return@get
                    }
                    val prize = getPrizeByKeyUseCase(year, category)
                    if (prize == null) {
                        call.respond(HttpStatusCode.NotFound, mapOf("message" to "Prize not found"))
                    } else {
                        call.respond(prize)
                    }
                }

                get("/{year}/{category}/laureates") {
                    val year = call.parameters["year"]?.toIntOrNull()
                    val category = call.parameters["category"]
                    if (year == null || category == null) {
                        call.respond(HttpStatusCode.BadRequest, mapOf("message" to "Invalid year/category"))
                        return@get
                    }
                    val laureates = getLaureatesUseCase(year, category)
                    if (laureates == null) {
                        call.respond(HttpStatusCode.NotFound, mapOf("message" to "Prize not found"))
                    } else {
                        call.respond(laureates)
                    }
                }
            }
        }
    }
}
