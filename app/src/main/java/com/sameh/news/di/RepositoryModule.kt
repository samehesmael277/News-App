package com.sameh.news.di

import com.sameh.news.data.repo.NewsRepo
import com.sameh.news.domain.repo.INewsRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindNewsRepository(
        newsRepo: NewsRepo
    ): INewsRepo
}