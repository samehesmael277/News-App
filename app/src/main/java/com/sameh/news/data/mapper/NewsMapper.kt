package com.sameh.news.data.mapper

import com.sameh.news.data.remote.Article
import com.sameh.news.data.remote.NewsDto
import com.sameh.news.domain.news.CachedArticle
import com.sameh.news.domain.news.News

fun NewsDto.toNews(): News {
    return News(this.articles)
}

fun List<Article>.toCachedArticles(): List<CachedArticle> {
    val cachedArticles = this.map { article ->
        CachedArticle(
            source = article.source,
            author = article.author,
            title = article.title,
            description = article.description,
            url = article.url,
            urlToImage = article.urlToImage,
            publishedAt = article.publishedAt,
            content = article.content
        )
    }
    return cachedArticles
}

fun List<CachedArticle>.toArticles(): List<Article> {
    val articles = this.map { cachedArticle ->
        Article(
            source = cachedArticle.source,
            author = cachedArticle.author,
            title = cachedArticle.title,
            description = cachedArticle.description,
            url = cachedArticle.url,
            urlToImage = cachedArticle.urlToImage,
            publishedAt = cachedArticle.publishedAt,
            content = cachedArticle.content
        )
    }
    return articles
}
