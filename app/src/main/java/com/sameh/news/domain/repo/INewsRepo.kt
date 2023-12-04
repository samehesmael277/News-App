package com.sameh.news.domain.repo

import com.sameh.news.domain.news.News
import com.sameh.news.domain.util.Resource

interface INewsRepo {

    suspend fun fetchDataFromApiAndCache(country: String, apiKey: String): Resource<News>

    suspend fun getLocalData(): Resource<News>
}