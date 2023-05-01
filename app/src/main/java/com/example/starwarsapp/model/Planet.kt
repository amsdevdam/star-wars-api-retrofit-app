package com.example.starwarsapp.model

data class Planet(
    var climate: String,
    val created: String,
    val diameter: String,
    val edited: String,
    val films: List<String>,
    val gravity: String,
    val name: String,
    val orbital_period: String,
    var population: String,
    val residents: List<String>,
    val rotation_period: String,
    var surface_water: String,
    var terrain: String,
    val url: String
) : StarWarsEntity()