package com.app.gitreposcompose.ui.main.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.app.gitreposcompose.model.RepositoriesModel
import com.app.gitreposcompose.model.ResultData
import com.app.gitreposcompose.usecase.DataUseCase
import kotlinx.coroutines.Dispatchers

class MainViewModel @ViewModelInject constructor(
    private val dataUseCase: DataUseCase
): ViewModel() {
    private var _repositoriesListLiveData: LiveData<ResultData<RepositoriesModel>> = MutableLiveData()
    val repositoriesListLiveData: LiveData<ResultData<RepositoriesModel>> get() = _repositoriesListLiveData

    fun getRepositoriesList(since: String) {
        _repositoriesListLiveData = liveData(Dispatchers.IO) {
            emit(ResultData.Loading())
            try {
                emit(dataUseCase.getRepositoriesList(since = since))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(ResultData.Exception())
            }
        }
    }
}