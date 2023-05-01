package com.example.starwarsapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.starwarsapp.adapter.CharactersAdapter
import com.example.starwarsapp.databinding.FragmentCharactersBinding
import com.example.starwarsapp.model.Character
import com.example.starwarsapp.model.StarWarsEntity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharactersFragment : EntitiesFragment() {
    private lateinit var binding: FragmentCharactersBinding
    @Inject lateinit var adapter: CharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharactersBinding.inflate(inflater, container, false)

        binding.charactersRecyclerView.adapter = adapter
        viewModel.initializeDataSet(StarWarsEntity.Type.CHARACTER)
        setupObservers()
        //populateRecyclerViewAdapter(StarWarsEntity.Type.CHARACTER)

        return binding.root
    }

    override fun onRetrieveDataSuccess(dataSet : List<StarWarsEntity>) {
        requireActivity().runOnUiThread {
            adapter.updateDataSet(dataSet as List<Character>)
        }
    }

    override fun onEntitiesUpdated(entities: List<StarWarsEntity>) {
        adapter.updateDataSet(entities as List<Character>)
    }

}