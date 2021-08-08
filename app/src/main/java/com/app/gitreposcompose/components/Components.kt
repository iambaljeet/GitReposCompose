package com.app.gitreposcompose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.app.gitreposcompose.model.RepositoriesModel

@Composable
fun ReposListItem(modifier: Modifier = Modifier, repositoriesModelItem: RepositoriesModel.RepositoriesModelItem) {
    Card(
            shape = RoundedCornerShape(5.dp),
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp)
    ) {
        Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                        .padding(10.dp)
        ) {
            Row(
                    verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberImagePainter(repositoriesModelItem.owner?.avatarUrl),
                    contentDescription = null,
                    modifier = Modifier.size(size = 20.dp)
                )
                Spacer(modifier = Modifier.size(height = 0.dp, width = 5.dp))
                Text(text = repositoriesModelItem.fullName?.substringBefore(delimiter = "/").toString(), fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = repositoriesModelItem.name.toString(), fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = repositoriesModelItem.description.toString(), fontSize = 14.sp)
        }
    }
}

@Composable
fun ReposList(repositoriesModel: RepositoriesModel?) {
    LazyColumn {
        if (!repositoriesModel.isNullOrEmpty()) {
            itemsIndexed(repositoriesModel) { index, data ->
                ReposListItem(repositoriesModelItem = data)
            }
        }
    }
}