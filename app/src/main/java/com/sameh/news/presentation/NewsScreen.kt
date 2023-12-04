package com.sameh.news.presentation

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.sameh.news.data.remote.Article
import com.squareup.picasso.Picasso
import com.squareup.picasso.PicassoProvider

@Composable
fun NewsScreen(navController: NavHostController, newsVM: NewsVM, sharedVM: SharedVM) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            newsVM.state.newsData?.let {
                NewsList(it.articles, Modifier.fillMaxSize(), navController, sharedVM)
            }
            if (newsVM.state.isLoading) {
                CircularProgressIndicator(
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            newsVM.state.error?.let { error ->
                Text(
                    text = error,
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun NewsList(
    articles: List<Article>,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    sharedVM: SharedVM
) {
    Column(
        modifier = modifier,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "News",
            style = typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            color = Color.Black
        )
        LazyColumn {
            items(articles) { article ->
                ArticleCard(article, navController = navController, sharedVM = sharedVM)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleCard(
    article: Article,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    sharedVM: SharedVM
) {
    Card(
        modifier = modifier
            .padding(16.dp),
        shape = RoundedCornerShape(20.dp),
        onClick = {
            sharedVM.addArticle(article)
            navController.navigate(Screen.ArticleScreen.route)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = article.title,
                style = typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                ),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(16.dp),
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            val painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = article.urlToImage)
                    .build()
            )
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}