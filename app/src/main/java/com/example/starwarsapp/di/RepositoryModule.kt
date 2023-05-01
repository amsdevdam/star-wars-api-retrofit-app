package com.example.starwarsapp.di

import com.example.starwarsapp.network.RetrofitInstance
import com.example.starwarsapp.repository.StarWarsRepository
import com.example.starwarsapp.repository.StarWarsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideStarWarsRepository(retrofitInstance: RetrofitInstance) : StarWarsRepository {
        return StarWarsRepositoryImpl(retrofitInstance)
    }
}