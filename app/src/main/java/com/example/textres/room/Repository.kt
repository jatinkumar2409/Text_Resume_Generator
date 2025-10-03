package com.example.textres.room

import androidx.room.Room
import kotlinx.coroutines.flow.Flow

class Repository(private val dao: Dao) {
    suspend fun insertProps(props : Properties){
        dao.insertProps(props)
    }
    suspend fun deleteProps(props : Properties){
        dao.deleteProps(props)
    }
    fun getProps() : Flow<Properties?>{
        return dao.getProps()
    }
}