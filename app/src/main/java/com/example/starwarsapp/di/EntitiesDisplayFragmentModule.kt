package com.example.starwarsapp.di

import com.example.starwarsapp.adapter.CharactersAdapter
import com.example.starwarsapp.adapter.PlanetsAdapter
import com.example.starwarsapp.adapter.SpeciesAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object EntitiesDisplayFragmentModule {

    @Provides
    fun providePlanetsAdapter() : PlanetsAdapter {
        return PlanetsAdapter()
    }

    @Provides
    fun provideCharactersAdapter() : CharactersAdapter {
        return CharactersAdapter()
    }

    @Provides
    fun provideSpeciesAdapter() : SpeciesAdapter {
        return SpeciesAdapter()
    }

}