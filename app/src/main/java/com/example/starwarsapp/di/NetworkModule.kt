package com.example.starwarsapp.di

import com.example.starwarsapp.api.StarWarsHttpApi
import com.example.starwarsapp.network.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://swapi.dev/api/"

    @Provides
    @Singleton
    fun provideRetrofitInstance(starWarsHttpApi: StarWarsHttpApi): RetrofitInstance {
        return RetrofitInstance(starWarsHttpApi)
    }

    @Provides
    @Singleton
    fun provideStarWarsHttpApi(): StarWarsHttpApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StarWarsHttpApi::class.java)
    }
}