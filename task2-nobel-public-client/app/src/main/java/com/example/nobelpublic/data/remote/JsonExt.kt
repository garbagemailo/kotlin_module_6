package com.example.nobelpublic.data.remote

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

fun JsonObject.string(name: String): String? = (this[name] as? JsonPrimitive)?.contentOrNull
fun JsonObject.obj(name: String): JsonObject? = this[name]?.jsonObject
fun JsonObject.arr(name: String): JsonArray = this[name]?.jsonArray ?: JsonArray(emptyList())
fun JsonObject.textEn(name: String): String? = this[name]?.jsonObject?.get("en")?.jsonPrimitive?.contentOrNull
fun JsonElement.asObjectOrNull(): JsonObject? = runCatching { jsonObject }.getOrNull()
