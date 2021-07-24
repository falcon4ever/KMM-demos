package com.epicwindmill.mvikotlindecomposekmmsample.api

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.*
import kotlinx.serialization.json.Json

class SwansonQuotesApi {
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