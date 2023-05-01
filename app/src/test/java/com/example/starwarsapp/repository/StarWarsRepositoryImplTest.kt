package com.example.starwarsapp.repository

import com.example.starwarsapp.api.StarWarsHttpApi
import com.example.starwarsapp.model.Character
import com.example.starwarsapp.model.Planet
import com.example.starwarsapp.model.Species
import com.example.starwarsapp.model.SwapiResponse
import com.example.starwarsapp.network.RetrofitInstance
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class StarWarsRepositoryImplTest {

    private lateinit var starWarsRepositoryImpl: StarWarsRepositoryImpl
    private lateinit var mockRetrofitInstance: RetrofitInstance
    private lateinit var mockStarWarsHttpApi: StarWarsHttpApi

    @Before
    fun setup() {
        mockStarWarsHttpApi = mockk<StarWarsHttpApi>()
        mockRetrofitInstance = mockk<RetrofitInstance>()
        starWarsRepositoryImpl = StarWarsRepositoryImpl(mockRetrofitInstance)
        mockRetrofitInstanceField()
    }

    private fun mockRetrofitInstanceField() {
        every {mockRetrofitInstance.starWarsHttpApi} returns mockStarWarsHttpApi
    }

    private fun prepareAndRetrieveCharacters(): List<Character> {
        return listOf(
            Character(
                "1992",
                "123",
                "123",
                "Brown",
                emptyList(),
                "Male",
                "Blonde",
                "1.75",
                "Earth",
                "80kg",
                "Brian",
                "White",
                listOf("Human"),
                emptyList(),
                "some_url",
                emptyList()
            ),
            Character(
                "1800",
                "999",
                "999",
                "Red",
                emptyList(),
                "N/A",
                "Blue",
                "3",
                "Mars",
                "20kg",
                "Zort",
                "Yellow",
                listOf("Bzorg"),
                emptyList(),
                "some_other_url",
                emptyList()
            )
        )
    }

    private fun prepareAndRetrievePlanets(): List<Planet> {
        return listOf(
            Planet(
                "Wet",
                "111",
                "10",
                "111",
                emptyList(),
                "1G",
                "Glop",
                "17",
                "30000000",
                emptyList(),
                "20",
                "53%",
                "Rocky",
                "some_planet_url"
            )
        )
    }

    private fun prepareAndRetrieveSpecies(): List<Species> {
        return listOf(
            Species(
                "2",
                "100",
                "Reptillian",
                "666",
                "Sentient",
                "666",
                "Purple",
                emptyList(),
                "Black",
                "Zbarba",
                "Justian",
                "Justic",
                emptyList(),
                "White",
                "species_url"
            )
        )
    }

    private fun mockStarWarsHttpApiMethodsSuccess() {
        val responseCharacters =
            Response.success(SwapiResponse<Character>(count = 2, next = "null", previous = "null", results = prepareAndRetrieveCharacters()))
        val responsePlanets =
            Response.success(SwapiResponse<Planet>(count = 1, next = "null", previous = "null", results = prepareAndRetrievePlanets()))
        val responseSpecies =
            Response.success(SwapiResponse<Species>(count = 1, next = "null", previous = "null", results = prepareAndRetrieveSpecies()))

        coEvery { mockStarWarsHttpApi.getCharacters() } returns responseCharacters
        coEvery { mockStarWarsHttpApi.getPlanets() } returns responsePlanets
        coEvery { mockStarWarsHttpApi.getSpecies() } returns responseSpecies
    }

    @Test
    fun `test entities retrieval Success`() = runTest{

        mockStarWarsHttpApiMethodsSuccess()
        assertEquals(starWarsRepositoryImpl.getCharacters(), prepareAndRetrieveCharacters())
    }

}