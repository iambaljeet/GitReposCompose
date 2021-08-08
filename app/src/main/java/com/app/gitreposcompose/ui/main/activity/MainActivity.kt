package com.app.gitreposcompose.ui.main.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.gitreposcompose.components.ReposList
import com.app.gitreposcompose.model.ResultData
import com.app.gitreposcompose.theming.GitReposComposeTheme
import com.app.gitreposcompose.ui.main.viewmodel.MainViewModel
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.statusBarsPadding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitReposComposeTheme {
                ProvideWindowInsets {
                    Surface(color = MaterialTheme.colors.background) {
                        ReposData()
                    }
                }
            }
        }
    }
}

@Composable
fun ReposData() {
    val mainViewModel: MainViewModel = viewModel()
    val dataState = mainViewModel.repositoriesListLiveData.observeAsState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (val resultData = dataState.value) {
                is ResultData.Loading -> {
                    LinearProgressIndicator(
                        modifier = Modifier.
                        fillMaxWidth()
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