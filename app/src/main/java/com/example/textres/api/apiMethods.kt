package com.example.textres.api

import com.example.textres.api.model.Data
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class apiMethods(private val httpClient : HttpClient) {

   suspend fun getData() : Data{
        return httpClient.get("https://expressjs-api-resume-random.onrender.com/resume?name=jatin").body()
    }
}