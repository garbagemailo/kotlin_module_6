package com.example.nobelpostgres.data.db

import com.example.nobelpostgres.data.repository.AuthRepositoryImpl
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.mindrot.jbcrypt.BCrypt
import java.time.LocalDateTime

object DatabaseFactory {
    fun init() {
        val config = HikariConfig().apply {
            jdbcUrl = System.getenv("DATABASE_URL") ?: "jdbc:postgresql://localhost:5432/nobel"
            username = System.getenv("DATABASE_USER") ?: "postgres"
            password = System.getenv("DATABASE_PASSWORD") ?: "postgres"
            driverClassName = "org.postgresql.Driver"
            maximumPoolSize = 5
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        }
        Database.connect(HikariDataSource(config))
        transaction {
            SchemaUtils.create(UsersTable, PrizesTable, LaureatesTable, UserPrizesTable)
        }
    }

    fun seedDefaultUsers(authRepository: AuthRepositoryImpl) {
        transaction {
            if (UsersTable.selectAll().count() == 0L) {
                authRepository.createUser(
                    username = "demo",
                    passwordHash = BCrypt.hashpw("demo123", BCrypt.gensalt()),
                    role = "user"
                )
                authRepository.createUser(
                    username = "admin",
                    passwordHash = BCrypt.hashpw("admin123", BCrypt.gensalt()),
                    role = "admin"
                )
            }
        }
    }
}
