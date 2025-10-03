package com.example.textres.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao{
    @Insert
    suspend fun insertProps(props : Properties)

    @Delete
    suspend fun deleteProps(props : Properties)

    @Query("Select * from properties")
    fun getProps () : Flow<Properties?>

}