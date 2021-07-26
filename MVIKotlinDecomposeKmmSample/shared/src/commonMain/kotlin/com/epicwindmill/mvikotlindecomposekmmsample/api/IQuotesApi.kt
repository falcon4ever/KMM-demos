package com.epicwindmill.mvikotlindecomposekmmsample.api

interface IQuotesApi {
    suspend fun getAllQuotes(): List<String>
}