package com.example.textres.api.model

import kotlinx.serialization.Serializable

@Serializable
data class Project(
    val description: String,
    val endDate: String,
    val startDate: String,
    val title: String
)