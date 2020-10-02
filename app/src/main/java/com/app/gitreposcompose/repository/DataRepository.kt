package com.app.gitreposcompose.repository

import com.app.gitreposcompose.model.RepositoriesModel
import com.app.gitreposcompose.network.ApiService
import javax.inject.Inject

class DataRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getRepositoriesList(since: String): RepositoriesModel {
        return apiService.getPublicRepositories(since = since)
    }
}