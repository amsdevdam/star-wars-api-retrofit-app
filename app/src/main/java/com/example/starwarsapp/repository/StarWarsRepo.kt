package com.example.starwarsapp.repository

import com.example.starwarsapp.model.*

interface StarWarsRepository {

    suspend fun getCharacters() : List<Character>?

    suspend fun getPlanets() : List<Planet>?

    suspend fun getSpecies() : List<Species>?

}