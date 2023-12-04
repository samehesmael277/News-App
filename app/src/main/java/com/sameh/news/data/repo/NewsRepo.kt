package com.sameh.news.data.repo

import com.sameh.news.data.local.NewsDao
import com.sameh.news.data.mapper.toArticles
import com.sameh.news.data.mapper.toCachedArticles
import com.sameh.news.data.mapper.toNews
import com.sameh.news.data.remote.NewsApi
import com.sameh.news.domain.news.News
import com.sameh.news.domain.repo.INewsRepo
import com.sameh.news.domain.util.Resource
import javax.inject.Inject

class NewsRepo @Inject constructor(
    private val api: NewsApi,
    private val dao: NewsDao
) : INewsRepo {

    override suspend fun fetchDataFromApiAndCache(country: String, apiKey: String): Resource<News> {
        return try {
            val result = Resource.Success(data = api.getNews(country, apiKey).toNews())
            result.data?.articles?.let { articles ->
                dao.clearAllArticles()
                dao.insertAllArticles(articles.toCachedArticles())
            }
            result
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error happened !")
        }
    }

    override suspend fun getLocalData(): Resource<News> {
        return try {
            val result = dao.getAllArticles()
            Resource.Success(data = News(result.toArticles()))
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error happened !")
        }
    }
}