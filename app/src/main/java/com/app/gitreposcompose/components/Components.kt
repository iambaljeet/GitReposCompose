package com.app.gitreposcompose.components

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.gitreposcompose.model.RepositoriesModel
import dev.chrisbanes.accompanist.picasso.PicassoImage

@Composable
fun ReposListItem(repositoriesModelItem: RepositoriesModel.RepositoriesModelItem) {
    Card(
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier.fillMaxWidth()
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
                PicassoImage(
                        data = repositoriesModelItem.owner?.avatarUrl.toString(),
                        fadeIn = true,
                        modifier = Modifier.preferredSize(size = 20.dp)
                )
                Spacer(modifier = Modifier.preferredSize(height = 0.dp, width = 5.dp))
                Text(text = repositoriesModelItem.fullName?.substringBefore(delimiter = "/").toString(), fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.preferredHeight(5.dp))
            Text(text = repositoriesModelItem.name.toString(), fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.preferredHeight(5.dp))
            Text(text = repositoriesModelItem.description.toString(), fontSize = 14.sp)
        }
    }
}

@Composable
fun ReposList(repositoriesModel: RepositoriesModel?) {
    ScrollableColumn {
        repositoriesModel?.forEach { repositoriesModelItem ->
            ReposListItem(repositoriesModelItem = repositoriesModelItem)
        }
    }
}