package com.example.starwarsapp.api

import com.example.starwarsapp.model.SwapiResponse
import retrofit2.Response
import retrofit2.http.GET
import com.example.starwarsapp.model.*
import retrofit2.http.Url

interface StarWarsHttpApi {

    @GET("people")
    suspend fun getCharacters() : Response<SwapiResponse<Character>>

    @GET("species")
    suspend fun getSpecies() : Response<SwapiResponse<Species>>

    @GET("planets")
    suspend fun getPlanets() : Response<SwapiResponse<Planet>>

    @GET
    suspend fun  getSingleSpecies(@Url url : String) : Response<Species>

    @GET
    suspend fun getSingleCharacter(@Url url: String): Response<Character>

    @GET
    suspend fun getSinglePlanet(@Url url: String): Response<Planet>


}