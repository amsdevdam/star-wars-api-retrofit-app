package com.example.starwarsapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.starwarsapp.model.Character
import com.example.starwarsapp.model.Planet
import com.example.starwarsapp.model.Species
import com.example.starwarsapp.model.StarWarsEntity
import com.example.starwarsapp.repository.StarWarsRepository
import com.example.starwarsapp.util.DispatcherProvider
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class EntitiesViewModelTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel : EntitiesViewModel
    lateinit var mockSwRepo : StarWarsRepository
    private val mockDispatcherProvider : DispatcherProvider = object : DispatcherProvider {
        override fun getMainDispatcher() = UnconfinedTestDispatcher()

        override fun getDefaultDispatcher() = UnconfinedTestDispatcher()

        override fun getIoDispatcher()  = UnconfinedTestDispatcher()

        override fun getUnconfinedDispatcher()  = UnconfinedTestDispatcher()

    }

    private fun mockRepositoryMethods() {
        coEvery { mockSwRepo.getCharacters() } returns listOf(
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

        coEvery { mockSwRepo.getPlanets() } returns listOf(
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

        coEvery { mockSwRepo.getSpecies() } returns listOf(
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
    private fun mockRepositoryMethodsWithNull() {

        coEvery { mockSwRepo.getCharacters() } returns null
        coEvery { mockSwRepo.getSpecies() } returns null
        coEvery { mockSwRepo.getPlanets() } returns null
    }

    @Before
    fun setup() {
        mockSwRepo = mockk<StarWarsRepository>()
        viewModel = EntitiesViewModel(mockSwRepo, mockDispatcherProvider)
    }

    @Test
    fun `test initializeDataSet with Character Type`() {
        mockRepositoryMethods()
        assertNull(viewModel.entities.value)
        viewModel.initializeDataSet(StarWarsEntity.Type.CHARACTER)
        assertNotNull(viewModel.entities.value)
        assertEquals(viewModel.entities.value!!.size, 2)
        val characterOne = Character(
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
        )
        assertEquals(viewModel.entities.value!![0], characterOne)

        val characterTwo = Character(
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

        assertEquals(viewModel.entities.value!![1], characterTwo)
    }

    @Test
    fun `test initializeDataSet with Planet Type`() {
        mockRepositoryMethods()
        assertNull(viewModel.entities.value)
        viewModel.initializeDataSet(StarWarsEntity.Type.PLANET)
        assertNotNull(viewModel.entities.value)
        assertEquals(viewModel.entities.value!!.size, 1)
        val planet = Planet(
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

        assertEquals(viewModel.entities.value!![0], planet)
    }

    @Test
    fun `test initializeDataSet with Species Type`() {
        mockRepositoryMethods()
        assertNull(viewModel.entities.value)
        viewModel.initializeDataSet(StarWarsEntity.Type.SPECIES)
        assertNotNull(viewModel.entities.value)
        assertEquals(viewModel.entities.value!!.size, 1)
        val species = Species(
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

        assertEquals(viewModel.entities.value!![0], species)
    }

    @Test
    fun `test onDataRetrievalErrorHandled`() {
        mockRepositoryMethodsWithNull()
        assertNull(viewModel.uiState.value)
        viewModel.initializeDataSet(StarWarsEntity.Type.CHARACTER)
        assertTrue(viewModel.uiState.value!!.dataRetrievalErrorIndicator)
        viewModel.onDataRetrievalErrorHandled()
        assertFalse(viewModel.uiState.value!!.dataRetrievalErrorIndicator)
    }
}