package com.example.starwarsapp.network

import com.example.starwarsapp.api.StarWarsHttpApi
import javax.inject.Inject

class RetrofitInstance @Inject constructor(
    val starWarsHttpApi: StarWarsHttpApi ) {

}