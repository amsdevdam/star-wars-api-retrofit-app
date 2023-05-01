package com.example.starwarsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsapp.model.StarWarsEntity
import com.example.starwarsapp.repository.StarWarsRepository
import com.example.starwarsapp.util.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EntitiesViewModel @Inject constructor(
    private val swRepo: StarWarsRepository,
    private val coroutineDispatcherProvider: DispatcherProvider
) : ViewModel() {

    data class UiState(
        val dataRetrievalErrorIndicator: Boolean = false,
    )

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> get() = _uiState

    private val _entities = MutableLiveData<List<StarWarsEntity>>()
    val entities get() = _entities

    fun initializeDataSet(entityType: StarWarsEntity.Type) {
        viewModelScope.launch(coroutineDispatcherProvider.getIoDispatcher()) {
            val items: List<StarWarsEntity>? = when (entityType) {
                StarWarsEntity.Type.CHARACTER -> swRepo.getCharacters()
                StarWarsEntity.Type.PLANET -> swRepo.getPlanets()
                StarWarsEntity.Type.SPECIES -> swRepo.getSpecies()
            }

            items?.let {
                _entities.postValue(it)
            } ?: run {
                updateDataRetrievalErrorIndicator(true)
            }
        }
    }

    private fun updateDataRetrievalErrorIndicator(value: Boolean) {
        _uiState.value = _uiState.value?.copy(dataRetrievalErrorIndicator = value) ?: UiState(
            dataRetrievalErrorIndicator = value
        )
    }

    fun onDataRetrievalErrorHandled() {
        updateDataRetrievalErrorIndicator(false)
    }
}