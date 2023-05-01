package com.example.starwarsapp.model

data class Species(
    val average_height: String,
    var average_lifespan: String,
    val classification: String,
    val created: String,
    var designation: String,
    val edited: String,
    val eye_colors: String,
    val films: List<String>,
    val hair_colors: String,
    var homeworld: String?,
    var language: String,
    val name: String,
    val people: List<String>,
    val skin_colors: String,
    val url: String
) : StarWarsEntity()