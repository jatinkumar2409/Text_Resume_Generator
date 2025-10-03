package com.example.textres.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.textres.Location.LocationFlow
import com.example.textres.api.apiMethods
import com.example.textres.homeScreen.Viewmodel
import com.example.textres.room.Repository
import com.example.textres.room.db
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        LocationFlow(get())
    }
    single<HttpClient> {
        HttpClient {
            install(ContentNegotiation){
                json(Json {
                    ignoreUnknownKeys = true
                })
            }

        }
    }
    single {
        apiMethods(get())
    }
    viewModel {
        Viewmodel(get() , get() , get())
    }
     single<db> {
         Room.databaseBuilder(get() , db::class.java , "db").build()
     }
    single {
        get<db>().getDao()
    }
    single {
        Repository(get())
    }
}