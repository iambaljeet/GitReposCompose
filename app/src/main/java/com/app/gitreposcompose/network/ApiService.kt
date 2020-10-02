package com.app.gitreposcompose.network

import com.app.gitreposcompose.model.RepositoriesModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(NetworkingConstants.URL_REPOSITORIES)
    suspend fun getPublicRepositories(
        @Query("since") since: String
    ): RepositoriesModel
}