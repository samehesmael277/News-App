package com.sameh.news.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sameh.news.domain.news.CachedArticle

@Database(entities = [CachedArticle::class], version = 1, exportSchema = false)
@TypeConverters(ArticleTypeConverter::class)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao
}