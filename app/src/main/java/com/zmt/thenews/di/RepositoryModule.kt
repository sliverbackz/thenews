package com.zmt.thenews.di

import com.zmt.thenews.model.local.datasource.AllNewsLocalDataSource
import com.zmt.thenews.model.local.datasource.NewsSourceLocalDataSource
import com.zmt.thenews.model.local.datasource.TopHeadlinesNewsLocalDataSource
import com.zmt.thenews.model.remote.datasource.NewsNetworkDataSource
import com.zmt.thenews.repository.AllNewsRepository
import com.zmt.thenews.repository.NewsSourceRepository
import com.zmt.thenews.repository.TopHeadlinesNewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideNewsRepository(
        newsNetworkDataSource: NewsNetworkDataSource,
        newsSourceLocalDataSource: NewsSourceLocalDataSource
    ): NewsSourceRepository {
        return NewsSourceRepository(newsNetworkDataSource, newsSourceLocalDataSource)
    }

    @Provides
    @ViewModelScoped
    fun provideAllNewsRepository(
        newsNetworkDataSource: NewsNetworkDataSource,
        allNewsLocalDataSource: AllNewsLocalDataSource
    ): AllNewsRepository {
        return AllNewsRepository(newsNetworkDataSource, allNewsLocalDataSource)
    }

    @Provides
    @ViewModelScoped
    fun provideTopHeadlinesNewsRepository(
        newsNetworkDataSource: NewsNetworkDataSource,
        topHeadlinesNewsLocalDataSource: TopHeadlinesNewsLocalDataSource
    ): TopHeadlinesNewsRepository {
        return TopHeadlinesNewsRepository(newsNetworkDataSource, topHeadlinesNewsLocalDataSource)
    }
}

