package com.example.starwarsapp.view

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.starwarsapp.model.StarWarsEntity
import com.example.starwarsapp.viewmodel.EntitiesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class EntitiesFragment : Fragment() {
    protected val viewModel: EntitiesViewModel by viewModels()

    abstract fun onRetrieveDataSuccess(dataSet: List<StarWarsEntity>)
    abstract fun onEntitiesUpdated(entities: List<StarWarsEntity>)

    protected fun setupObservers() {
        viewModel.entities.observe(viewLifecycleOwner) {
            onEntitiesUpdated(it)
        }

        viewModel.uiState.observe(viewLifecycleOwner) {
            if (it.dataRetrievalErrorIndicator) {
                Toast.makeText(
                    this@EntitiesFragment.requireContext(),
                    "Error retrieving data!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                viewModel.onDataRetrievalErrorHandled()
            }
        }
    }
}