package com.sameh.news.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sameh.news.data.remote.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedVM @Inject constructor() : ViewModel() {

    var article by mutableStateOf<Article?>(null)
        private set

    fun addArticle(newArticle: Article) {
        article = newArticle
    }
}