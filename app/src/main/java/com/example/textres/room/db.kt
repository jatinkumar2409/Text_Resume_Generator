package com.example.textres.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Properties::class] , version = 1 , exportSchema = false)
abstract class db : RoomDatabase() {
    abstract fun getDao() : Dao
}