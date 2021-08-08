package com.app.gitreposcompose.repository

import com.app.gitreposcompose.model.RepositoriesModel
import com.app.gitreposcompose.model.ResultData
import com.app.gitreposcompose.network.ApiService
import javax.inject.Inject

class DataRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getRepositoriesList(since: String, result: (ResultData<RepositoriesModel>) -> Unit) {
        result(ResultData.Loading())
        val repositoriesModel = apiService.getPublicRepositories(since = since)

        val resultData = when(repositoriesModel.isNotEmpty()) {
            true -> {
                ResultData.Success(repositoriesModel)
            }
            else -> {
                ResultData.Failed()
            }
        }
        result(resultData)
    }
}