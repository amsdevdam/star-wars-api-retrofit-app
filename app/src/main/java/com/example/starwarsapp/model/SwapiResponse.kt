package com.example.starwarsapp.model

data class SwapiResponse<out T>(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<T>
)

