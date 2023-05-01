package com.example.starwarsapp.model

sealed class StarWarsEntity {
    enum class Type() {
        CHARACTER,
        PLANET,
        SPECIES
    }
}