package com.sameh.news.domain.news

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sameh.news.data.remote.Source

@Entity(tableName = "articles")
data class CachedArticle(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val source: Source,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)
