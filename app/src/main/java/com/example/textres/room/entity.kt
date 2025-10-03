package com.example.textres.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Properties(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val color : String ,
    val txtColor : String ,
    val fontSize : Int
)