package com.example.starwarsapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.starwarsapp.adapter.SpeciesAdapter
import com.example.starwarsapp.databinding.FragmentSpeciesBinding
import com.example.starwarsapp.model.Species
import com.example.starwarsapp.model.StarWarsEntity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SpeciesFragment : EntitiesFragment() {
    private lateinit var binding : FragmentSpeciesBinding
    @Inject lateinit var adapter: SpeciesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSpeciesBinding.inflate(inflater, container, false)

        binding.speciesRecyclerView.adapter = adapter
        viewModel.initializeDataSet(StarWarsEntity.Type.SPECIES)
        setupObservers()

        return binding.root
    }

    override fun onRetrieveDataSuccess(dataSet: List<StarWarsEntity>) {
        requireActivity().runOnUiThread {
            adapter.updateDataSet(dataSet as List<Species>)
        }
    }

    override fun onEntitiesUpdated(entities: List<StarWarsEntity>) {
        adapter.updateDataSet(entities as List<Species>)
    }
}