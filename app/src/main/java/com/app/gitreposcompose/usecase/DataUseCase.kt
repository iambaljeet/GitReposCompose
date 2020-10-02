package com.app.gitreposcompose.usecase

import com.app.gitreposcompose.model.RepositoriesModel
import com.app.gitreposcompose.repository.DataRepository
import com.app.gitreposcompose.model.ResultData
import javax.inject.Inject

class DataUseCase @Inject constructor(
    private val dataRepository: DataRepository
) {
    suspend fun getRepositoriesList(since: String): ResultData<RepositoriesModel> {
        val repositoriesModel = dataRepository.getRepositoriesList(since = since)

        val resultData = when(repositoriesModel.isNotEmpty()) {
            true -> {
                ResultData.Success(repositoriesModel)
            }
            else -> {
                ResultData.Failed()
            }
        }
        return resultData
    }
}