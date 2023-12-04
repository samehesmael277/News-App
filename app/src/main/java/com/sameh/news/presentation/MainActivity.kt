package com.sameh.news.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.sameh.news.R
import com.sameh.news.presentation.ui.theme.NewsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val newsVM: NewsVM by viewModels()

    private val sharedVM: SharedVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        newsVM.getNewsFromApi("us", getString(R.string.api_key))

        setContent {
            NewsTheme {
                Navigation(newsVM, sharedVM)
            }
        }
    }
}