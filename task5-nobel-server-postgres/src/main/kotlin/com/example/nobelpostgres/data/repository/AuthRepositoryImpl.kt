package com.example.nobelpostgres.data.repository

import com.example.nobelpostgres.data.db.UsersTable
import com.example.nobelpostgres.domain.DbUser
import com.example.nobelpostgres.domain.UserProfile
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class AuthRepositoryImpl {
    fun createUser(username: String, passwordHash: String, role: String) {
        UsersTable.insert {
            it[UsersTable.username] = username
            it[UsersTable.passwordHash] = passwordHash
            it[UsersTable.role] = role
        }
    }

    fun findByUsername(username: String): DbUser? = transaction {
        UsersTable.selectAll().where { UsersTable.username eq username }
            .singleOrNull()
            ?.toDbUser()
    }

    fun findProfileById(userId: Int): UserProfile? = transaction {
        UsersTable.selectAll().where { UsersTable.id eq userId }
            .singleOrNull()
            ?.let {
                UserProfile(
                    id = it[UsersTable.id],
                    username = it[UsersTable.username],
                    role = it[UsersTable.role]
                )
            }
    }

    private fun ResultRow.toDbUser() = DbUser(
        id = this[UsersTable.id],
        username = this[UsersTable.username],
        passwordHash = this[UsersTable.passwordHash],
        role = this[UsersTable.role]
    )
}
