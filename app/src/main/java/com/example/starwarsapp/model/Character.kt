package com.example.starwarsapp.model

data class Character (
    val birth_year: String,
    val created: String,
    val edited: String,
    val eye_color: String,
    val films: List<String>,
    var gender: String,
    val hair_color: String,
    val height: String,
    var homeworld: String,
    val mass: String,
    val name: String,
    val skin_color: String,
    var species: List<String>,
    val starships: List<String>,
    val url: String,
    val vehicles: List<String>
) : StarWarsEntity()