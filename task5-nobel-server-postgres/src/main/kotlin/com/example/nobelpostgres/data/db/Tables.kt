package com.example.nobelpostgres.data.db

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object UsersTable : Table("users") {
    val id = integer("id").autoIncrement()
    val username = varchar("username", 100).uniqueIndex()
    val passwordHash = varchar("password_hash", 255)
    val role = varchar("role", 50)
    override val primaryKey = PrimaryKey(id)
}

object PrizesTable : Table("prizes") {
    val id = integer("id").autoIncrement()
    val awardYear = integer("award_year")
    val category = varchar("category", 100)
    val categoryTitle = varchar("category_title", 255)
    val topMotivation = text("top_motivation").nullable()
    val detailLink = text("detail_link").nullable()
    val updatedAt = datetime("updated_at")
    override val primaryKey = PrimaryKey(id)
    init {
        uniqueIndex(awardYear, category)
    }
}

object LaureatesTable : Table("laureates") {
    val id = integer("id").autoIncrement()
    val externalId = varchar("external_id", 100).nullable()
    val prizeId = integer("prize_id").references(PrizesTable.id, onDelete = ReferenceOption.CASCADE)
    val fullName = varchar("full_name", 255)
    val portion = varchar("portion", 20).nullable()
    val motivation = text("motivation").nullable()
    val birthCountry = varchar("birth_country", 255).nullable()
    val birthPlace = varchar("birth_place", 255).nullable()
    val portraitUrl = text("portrait_url").nullable()
    override val primaryKey = PrimaryKey(id)
}

object UserPrizesTable : Table("user_prizes") {
    val userId = integer("user_id").references(UsersTable.id, onDelete = ReferenceOption.CASCADE)
    val prizeId = integer("prize_id").references(PrizesTable.id, onDelete = ReferenceOption.CASCADE)
    val addedAt = datetime("added_at")
    override val primaryKey = PrimaryKey(userId, prizeId)
}
