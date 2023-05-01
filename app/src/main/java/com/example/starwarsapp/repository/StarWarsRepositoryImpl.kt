package com.example.starwarsapp.repository

import android.util.Log
import com.example.starwarsapp.model.Character
import com.example.starwarsapp.model.Planet
import com.example.starwarsapp.model.Species
import com.example.starwarsapp.model.StarWarsEntity
import com.example.starwarsapp.network.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.util.*
import javax.inject.Inject

class StarWarsRepositoryImpl @Inject constructor(private val retrofitInstance: RetrofitInstance) :
    StarWarsRepository {

    @Suppress("UNCHECKED_CAST")
    override suspend fun getCharacters(): List<Character>? =
        getResults(StarWarsEntity.Type.CHARACTER) as? List<Character>?

    @Suppress("UNCHECKED_CAST")
    override suspend fun getPlanets(): List<Planet>? =
        getResults(StarWarsEntity.Type.PLANET) as? List<Planet>?

    @Suppress("UNCHECKED_CAST")
    override suspend fun getSpecies(): List<Species>? =
        getResults(StarWarsEntity.Type.SPECIES) as? List<Species>?

    private suspend fun getResults(type: StarWarsEntity.Type): List<StarWarsEntity>? {
        val response = try {
            when (type) {
                StarWarsEntity.Type.CHARACTER -> retrofitInstance.starWarsHttpApi.getCharacters()
                StarWarsEntity.Type.PLANET -> retrofitInstance.starWarsHttpApi.getPlanets()
                StarWarsEntity.Type.SPECIES -> retrofitInstance.starWarsHttpApi.getSpecies()
            }
        } catch (e: Exception) {
            when (e) {
                is IOException -> Log.e(
                    "StarWarsRepositoryImpl",
                    "IOException, you might not have internet connection"
                )
                is HttpException -> Log.e(
                    "StarWarsRepositoryImpl",
                    "HttpException, unexpected response"
                )
                else -> Log.e("StarWarsRepositoryImpl", e.message ?: "Unknown error")
            }

            return null
        }

        return if (response.isSuccessful && response.body() != null) {
            formatResults(response.body()!!.results)
        } else {
            Log.e("StarWarsRepositoryImpl", "Response not successful")
            null
        }
    }

    private suspend fun getSingleEntity(type: StarWarsEntity.Type, url: String): StarWarsEntity? {
        val response = try {
            when (type) {
                StarWarsEntity.Type.CHARACTER -> retrofitInstance.starWarsHttpApi.getSingleCharacter(
                    url
                )
                StarWarsEntity.Type.PLANET -> retrofitInstance.starWarsHttpApi.getSinglePlanet(url)
                StarWarsEntity.Type.SPECIES -> retrofitInstance.starWarsHttpApi.getSingleSpecies(url)
            }

        } catch (e: Exception) {
            when (e) {
                is IOException -> Log.e(
                    "StarWarsRepositoryImpl",
                    "IOException, you might not have internet connection"
                )
                is HttpException -> Log.e(
                    "StarWarsRepositoryImpl",
                    "HttpException, unexpected response"
                )
                else -> Log.e("StarWarsRepositoryImpl", e.message ?: "Unknown error")
            }

            return null
        }

        return if (response.isSuccessful && response.body() != null) {
            response.body()
        } else {
            Log.e("StarWarsRepositoryImpl", "Response not successful")
            null
        }
    }

    private suspend fun formatResults(results: List<StarWarsEntity>): List<StarWarsEntity> {
        val formattedList = mutableListOf<StarWarsEntity>()
        results.forEach {
            val entity: StarWarsEntity = when (it) {
                is Character -> formatCharacter(it)
                is Planet -> formatPlanet(it)
                is Species -> formatSpecies(it)
            }
            formattedList.add(entity)
        }

        return formattedList
    }

    private suspend fun formatSpecies(species: Species): Species {
        val formattedSpecies = species.copy()
        formattedSpecies.designation =
            formattedSpecies.designation.replaceFirstChar { it.uppercaseChar() }

        formattedSpecies.homeworld?.let {
            formattedSpecies.homeworld =
                (getSingleEntity(StarWarsEntity.Type.PLANET, it) as Planet).name
        } ?: run { formattedSpecies.homeworld = "N/A" }

        formattedSpecies.average_lifespan =
            formattedSpecies.average_lifespan.replaceFirstChar { it.uppercaseChar() }

        if (formattedSpecies.language == "n/a") formattedSpecies.language = "N/A"


        return formattedSpecies
    }

    private fun formatPlanet(planet: Planet): Planet {
        val formattedPlanet = planet.copy()
        formattedPlanet.terrain = formattedPlanet.terrain.replaceFirstChar { it.uppercaseChar() }

        formattedPlanet.surface_water = "%${formattedPlanet.surface_water}"

        formattedPlanet.climate = formattedPlanet.climate.replaceFirstChar { it.uppercaseChar() }
        formattedPlanet.population =
            formattedPlanet.population.replaceFirstChar { it.uppercaseChar() }

        return formattedPlanet
    }

    private suspend fun formatCharacter(character: Character): Character {
        val formattedCharacter = character.copy()
        if (formattedCharacter.gender == "n/a") {
            formattedCharacter.gender = "N/A"
        } else {
            formattedCharacter.gender =
                formattedCharacter.gender.replaceFirstChar { it.uppercaseChar() }
        }

        val species = mutableListOf<String>()
        if (formattedCharacter.species.isEmpty()) {
            species.add("Human")
        } else {
            formattedCharacter.species.forEach {
                species.add(
                    (getSingleEntity(StarWarsEntity.Type.SPECIES, it) as? Species)?.name
                        ?: "Unknown"
                )
            }
        }

        formattedCharacter.species = species

        lateinit var homeworld: String
        CoroutineScope(Dispatchers.IO).launch {
            homeworld = (getSingleEntity(
                StarWarsEntity.Type.PLANET,
                formattedCharacter.homeworld
            ) as? Planet)?.name ?: "Unknown"
            formattedCharacter.homeworld = homeworld
        }

        return formattedCharacter
    }
}