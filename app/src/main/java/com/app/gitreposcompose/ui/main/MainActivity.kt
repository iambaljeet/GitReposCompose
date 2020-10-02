package com.app.gitreposcompose.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
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
                    mainViewModel.getRepositoriesList("")
                    ReposData(mainViewModel)
                }
            }
        }
    }
}

@Composable
fun ReposData(mainViewModel: MainViewModel) {
    val dataState = mainViewModel.repositoriesListLiveData.observeAsState()

    when(val resultData = dataState.value) {
        is ResultData.Loading -> {
            Text(text = "Loading Data")
        }
        is ResultData.Success -> {
            val repositoriesModel = resultData.data
            Text(text = "Data: $repositoriesModel")
        }
        is ResultData.Failed -> {
            Text(text = "Failed")
        }
        is ResultData.Exception -> {
            Text(text = "Something went wrong Please try again!")
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GitReposComposeTheme {
        Greeting("Android")
    }
}