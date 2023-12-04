package com.sameh.news.presentation

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun ArticleScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    sharedVM: SharedVM
) {
    val article = sharedVM.article

    article?.let {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = modifier
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack, contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                        )
                    }
                    Text(
                        text = "Article",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        ),
                        modifier = Modifier,
                        color = Color.Black
                    )
                    ShareArticleIcon(article.title, article.url)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    ),
                    modifier = Modifier,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(16.dp))
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
                        .height(200.dp),
                    contentScale = ContentScale.FillBounds
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Source: ${article.source.name}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    ),
                    modifier = Modifier
                        .align(Alignment.End),
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(16.dp))
                article.description?.let { description ->
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        ),
                        modifier = Modifier
                            .align(Alignment.Start),
                        color = Color.Black
                    )
                }
                article.content?.let { content ->
                    Text(
                        text = content,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        ),
                        modifier = Modifier
                            .align(Alignment.Start),
                        color = Color.Black
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Published At: ${article.publishedAt}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    ),
                    modifier = Modifier
                        .align(Alignment.End),
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun ShareArticleIcon(articleTitle: String, articleUrl: String) {
    val context = LocalContext.current
    IconButton(
        onClick = {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "$articleTitle\n$articleUrl")
                type = "text/plain"
            }
            context.startActivity(Intent.createChooser(shareIntent, "Share Article"))
        }
    ) {
        Icon(
            imageVector = Icons.Default.Share, contentDescription = null,
            modifier = Modifier
                .size(24.dp)
        )
    }
}
