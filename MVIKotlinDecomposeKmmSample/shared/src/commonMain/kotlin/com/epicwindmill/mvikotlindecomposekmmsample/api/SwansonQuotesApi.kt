package com.epicwindmill.mvikotlindecomposekmmsample.api

import io.ktor.client.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json

class QuotesApi {
    private val httpClient = HttpClient {
        install(JsonFeature) {
            val json = Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            }
            serializer = KotlinxSerializer(json)
        }
    }

    suspend fun getAllQuotes(): List<String> {
        return httpClient.get(QUOTES_ENDPOINT)
    }

    companion object {
        private const val QUOTES_ENDPOINT = "https://ron-swanson-quotes.herokuapp.com/v2/quotes"
    }
}

class SwansonQuotes {
    private val api = QuotesApi()

    @Throws(Exception::class) suspend fun getAllQuotes(): List<String> {
        return api.getAllQuotes()
    }
}