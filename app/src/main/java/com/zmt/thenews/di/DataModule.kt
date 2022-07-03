package com.zmt.thenews.di

import com.zmt.thenews.model.local.db.AppDatabase
import com.zmt.thenews.model.local.db.news.dao.AllNewsDao
import com.zmt.thenews.model.local.db.news.dao.NewsSourceDao
import com.zmt.thenews.model.local.db.news.dao.TopHeadlinesNewsDao
import com.zmt.thenews.model.remote.service.NewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideNewsSourceDao(database: AppDatabase): NewsSourceDao {
        return database.newsSourceDao()
    }

    @Provides
    fun provideTopHeadlinesNewsDao(database: AppDatabase): TopHeadlinesNewsDao {
        return database.topHeadlinesNewsDao()
    }

    @Provides
    fun provideAllNewsDao(database: AppDatabase): AllNewsDao {
        return database.allNewsDao()
    }

    @Provides
    fun provideMovieService(retrofit: Retrofit): NewsService {
        return retrofit.create(NewsService::class.java)
    }
}