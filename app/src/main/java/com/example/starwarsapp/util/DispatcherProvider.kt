package com.example.starwarsapp.util

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {

    fun getMainDispatcher() : CoroutineDispatcher
    fun getDefaultDispatcher() : CoroutineDispatcher
    fun getIoDispatcher() : CoroutineDispatcher
    fun getUnconfinedDispatcher() : CoroutineDispatcher
}