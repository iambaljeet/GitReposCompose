package com.app.gitreposcompose.di

import com.app.gitreposcompose.network.ApiService
import com.app.gitreposcompose.repository.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object DataRepositoryModule {

    @Provides
    fun provideDataRepository(apiService: ApiService): DataRepository {
        return DataRepository(apiService)
    }
}