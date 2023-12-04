package com.sameh.news.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sameh.news.domain.news.CachedArticle

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllArticles(articles: List<CachedArticle>)

    @Query("SELECT * FROM articles")
    suspend fun getAllArticles(): List<CachedArticle>

    @Query("DELETE FROM articles")
    suspend fun clearAllArticles()
}