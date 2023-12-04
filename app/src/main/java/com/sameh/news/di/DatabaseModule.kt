package com.sameh.news.di

import android.content.Context
import androidx.room.Room
import com.sameh.news.data.local.NewsDao
import com.sameh.news.data.local.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    @Named("room_db_name")
    fun provideNewsDBName(): String {
        return "news_db"
    }

    @Singleton
    @Provides
    fun provideNewsRoomDB(
        @ApplicationContext context: Context,
        @Named("room_db_name") roomDBName: String
    ): NewsDatabase =
        Room.databaseBuilder(context, NewsDatabase::class.java, roomDBName).build()

    @Singleton
    @Provides
    fun provideNewsDao(db: NewsDatabase): NewsDao = db.newsDao()
}