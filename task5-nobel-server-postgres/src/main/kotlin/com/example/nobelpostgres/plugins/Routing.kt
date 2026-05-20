package com.example.nobelpostgres.plugins

import com.example.nobelpostgres.data.repository.AuthRepositoryImpl
import com.example.nobelpostgres.data.repository.PrizeRepositoryImpl
import com.example.nobelpostgres.domain.AuthService
import com.example.nobelpostgres.domain.LoginRequest
import com.example.nobelpostgres.domain.LoginUseCase
import com.example.nobelpostgres.domain.SyncPrizesUseCase
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.html.respondHtml
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import kotlinx.html.body
import kotlinx.html.div
import kotlinx.html.head
import kotlinx.html.id
import kotlinx.html.link
import kotlinx.html.script
import kotlinx.html.unsafe

fun Application.configureRouting(
    authService: AuthService,
    loginUseCase: LoginUseCase,
    prizeRepository: PrizeRepositoryImpl,
    syncPrizesUseCase: SyncPrizesUseCase
) {
    val authRepository = AuthRepositoryImpl()

    routing {
        get("/") {
            call.respond(mapOf("service" to "Nobel Prize API (PostgreSQL)", "docs" to "/docs"))
        }

        get("/openapi.yaml") {
            call.respondText(openApiYaml, ContentType.Text.Plain)
        }

        get("/docs") {
            call.respondHtml {
                head {
                    link(rel = "stylesheet", href = "https://unpkg.com/swagger-ui-dist@5/swagger-ui.css")
                }
                body {
                    div { id = "swagger-ui" }
                    script(src = "https://unpkg.com/swagger-ui-dist@5/swagger-ui-bundle.js") {}
                    script {
                        unsafe {
                            +"window.onload = () => SwaggerUIBundle({ url: '/openapi.yaml', dom_id: '#swagger-ui' });"
                        }
                    }
                }
            }
        }

        post("/login") {
            val request = call.receive<LoginRequest>()
            val response = loginUseCase(request)
            if (response == null) {
                call.respond(HttpStatusCode.Unauthorized, mapOf("message" to "Invalid credentials"))
            } else {
                call.respond(response)
            }
        }

        get("/prizes") {
            val year = call.request.queryParameters["year"]?.toIntOrNull()
            val category = call.request.queryParameters["category"]
            call.respond(prizeRepository.getPrizes(year, category))
        }

        get("/prizes/{year}/{category}") {
            val year = call.parameters["year"]?.toIntOrNull()
            val category = call.parameters["category"]
            if (year == null || category == null) {
                call.respond(HttpStatusCode.BadRequest, mapOf("message" to "Invalid year/category"))
                return@get
            }
            val prize = prizeRepository.getPrizeByKey(year, category)
            if (prize == null) call.respond(HttpStatusCode.NotFound, mapOf("message" to "Prize not found"))
            else call.respond(prize)
        }

        get("/prizes/{year}/{category}/laureates") {
            val year = call.parameters["year"]?.toIntOrNull()
            val category = call.parameters["category"]
            if (year == null || category == null) {
                call.respond(HttpStatusCode.BadRequest, mapOf("message" to "Invalid year/category"))
                return@get
            }
            call.respond(prizeRepository.getLaureates(year, category))
        }


        post("/sync") {
            val refreshed = syncPrizesUseCase(force = true)
            call.respond(mapOf("refreshed" to refreshed))
        }

        authenticate("auth-jwt") {
            route("/users/me") {
                get {
                    val userId = authService.userId(call.principal<JWTPrincipal>())
                    val profile = userId?.let { authRepository.findProfileById(it) }
                    if (profile == null) call.respond(HttpStatusCode.NotFound, mapOf("message" to "User not found"))
                    else call.respond(profile)
                }

                get("/prizes") {
                    val userId = authService.userId(call.principal<JWTPrincipal>())
                    if (userId == null) {
                        call.respond(HttpStatusCode.Unauthorized)
                        return@get
                    }
                    call.respond(prizeRepository.getFavorites(userId))
                }

                post("/prizes/{prizeId}") {
                    val userId = authService.userId(call.principal<JWTPrincipal>())
                    val prizeId = call.parameters["prizeId"]?.toIntOrNull()
                    if (userId == null || prizeId == null) {
                        call.respond(HttpStatusCode.BadRequest, mapOf("message" to "Invalid data"))
                        return@post
                    }
                    prizeRepository.addFavorite(userId, prizeId)
                    call.respond(HttpStatusCode.Created, mapOf("message" to "Prize added to favorites"))
                }

                delete("/prizes/{prizeId}") {
                    val userId = authService.userId(call.principal<JWTPrincipal>())
                    val prizeId = call.parameters["prizeId"]?.toIntOrNull()
                    if (userId == null || prizeId == null) {
                        call.respond(HttpStatusCode.BadRequest, mapOf("message" to "Invalid data"))
                        return@delete
                    }
                    prizeRepository.removeFavorite(userId, prizeId)
                    call.respond(mapOf("message" to "Prize removed from favorites"))
                }
            }
        }
    }
}

private val openApiYaml = """
openapi: 3.0.3
info:
  title: Nobel Prize API
  version: 1.0.0
servers:
  - url: http://localhost:8081
paths:
  /login:
    post:
      summary: Login and get JWT token
      requestBody:
        required: true
        content:
          application/json:
            schema:
              type: object
              properties:
                username: { type: string }
                password: { type: string }
      responses:
        '200':
          description: JWT token
  /prizes:
    get:
      summary: List prizes with optional filters
      parameters:
        - in: query
          name: year
          schema: { type: integer }
        - in: query
          name: category
          schema: { type: string }
      responses:
        '200':
          description: Prize list
  /prizes/{year}/{category}:
    get:
      summary: Prize details
      parameters:
        - in: path
          name: year
          required: true
          schema: { type: integer }
        - in: path
          name: category
          required: true
          schema: { type: string }
      responses:
        '200':
          description: Prize details
  /prizes/{year}/{category}/laureates:
    get:
      summary: Laureates of a prize
      parameters:
        - in: path
          name: year
          required: true
          schema: { type: integer }
        - in: path
          name: category
          required: true
          schema: { type: string }
      responses:
        '200':
          description: Laureates
  /users/me:
    get:
      security:
        - bearerAuth: []
      responses:
        '200':
          description: Current profile
  /users/me/prizes:
    get:
      security:
        - bearerAuth: []
      responses:
        '200':
          description: Favorite prizes
  /users/me/prizes/{prizeId}:
    post:
      security:
        - bearerAuth: []
      parameters:
        - in: path
          name: prizeId
          required: true
          schema: { type: integer }
      responses:
        '201':
          description: Added to favorites
    delete:
      security:
        - bearerAuth: []
      parameters:
        - in: path
          name: prizeId
          required: true
          schema: { type: integer }
      responses:
        '200':
          description: Removed from favorites
components:
  securitySchemes:
    bearerAuth:
      type: http
      scheme: bearer
      bearerFormat: JWT
""".trimIndent()
