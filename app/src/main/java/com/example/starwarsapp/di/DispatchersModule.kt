package com.example.starwarsapp.di

import com.example.starwarsapp.util.DispatcherProvider
import com.example.starwarsapp.util.DispatcherProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

    @Provides
    @Singleton
    fun provideCoroutineDispatcherProvider() : DispatcherProvider {
        return DispatcherProviderImpl()
    }
}