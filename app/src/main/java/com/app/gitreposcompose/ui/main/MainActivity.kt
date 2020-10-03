package com.app.gitreposcompose.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import com.app.gitreposcompose.ReposList
import com.app.gitreposcompose.model.ResultData
import com.app.gitreposcompose.theming.GitReposComposeTheme
import com.app.gitreposcompose.ui.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitReposComposeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    mainViewModel.getRepositoriesList("1")
                    ReposData(mainViewModel)
                }
            }
        }
    }
}

@Composable
fun ReposData(mainViewModel: MainViewModel) {
    val dataState = mainViewModel.repositoriesListLiveData.observeAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (val resultData = dataState.value) {
            is ResultData.Loading -> {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth()
                )
            }
            is ResultData.Success -> {
                val repositoriesModel = resultData.data
                ReposList(repositoriesModel = repositoriesModel)
            }
            is ResultData.Failed -> {
                Text(text = "Failed to load data.")
            }
            is ResultData.Exception -> {
                Text(text = "Something went wrong. Please try again!")
            }
        }
    }
}