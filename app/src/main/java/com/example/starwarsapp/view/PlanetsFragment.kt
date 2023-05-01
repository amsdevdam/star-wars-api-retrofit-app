package com.example.starwarsapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.starwarsapp.adapter.PlanetsAdapter
import com.example.starwarsapp.databinding.FragmentPlanetsBinding
import com.example.starwarsapp.model.Planet
import com.example.starwarsapp.model.StarWarsEntity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlanetsFragment : EntitiesFragment() {
    private lateinit var binding : FragmentPlanetsBinding
    @Inject lateinit var adapter: PlanetsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlanetsBinding.inflate(inflater, container, false)

        binding.planetsRecyclerView.adapter = adapter
        viewModel.initializeDataSet(StarWarsEntity.Type.PLANET)
        setupObservers()
        //populateRecyclerViewAdapter(StarWarsEntity.Type.PLANET)

        return binding.root
    }

    override fun onRetrieveDataSuccess(dataSet: List<StarWarsEntity>) {
        requireActivity().runOnUiThread {
            adapter.updateDataSet(dataSet as List<Planet>)
        }
    }

    override fun onEntitiesUpdated(entities: List<StarWarsEntity>) {
        adapter.updateDataSet(entities as List<Planet>)
    }
}