package com.example.starwarsapp.util

import kotlinx.coroutines.Dispatchers

class DispatcherProviderImpl : DispatcherProvider {
    override fun getMainDispatcher() = Dispatchers.Main

    override fun getDefaultDispatcher() = Dispatchers.Default

    override fun getIoDispatcher() = Dispatchers.IO

    override fun getUnconfinedDispatcher() = Dispatchers.Unconfined
}