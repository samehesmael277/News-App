package com.sameh.news.presentation

import com.sameh.news.domain.news.News

data class NewsState(
    val newsData: News? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)