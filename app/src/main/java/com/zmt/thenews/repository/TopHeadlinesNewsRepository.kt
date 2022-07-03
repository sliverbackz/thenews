package com.zmt.thenews.repository

import com.zmt.thenews.helper.State
import com.zmt.thenews.model.Params
import com.zmt.thenews.model.local.datasource.TopHeadlinesNewsLocalDataSource
import com.zmt.thenews.model.local.db.news.entity.TopHeadlinesNews
import com.zmt.thenews.model.remote.datasource.NewsNetworkDataSource
import com.zmt.thenews.model.remote.helper.Resource
import com.zmt.thenews.repository.mapper.TopHeadlinesNewsMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class TopHeadlinesNewsRepository @Inject constructor(
    private val newsNetworkDataSource: NewsNetworkDataSource,
    private val topHeadlinesNewsLocalDataSource: TopHeadlinesNewsLocalDataSource
) {
    private val mapper = TopHeadlinesNewsMapper()

    suspend fun getTopHeadlinesNews(params: Params): Flow<State<List<TopHeadlinesNews>?>> {
        return newsNetworkDataSource.getTopHeadlines(params).map {
            when (it) {
                is Resource.Success -> {
                    val data = it.data.articles?.map(mapper::map) ?: emptyList()
                    topHeadlinesNewsLocalDataSource.insertAll(data)
                    topHeadlinesNewsLocalDataSource.getAllNoFlow()?.forEach {
                        Timber.i(it.title)
                    }
                    State.Success(topHeadlinesNewsLocalDataSource.getAllNoFlow())
                }
                is Resource.Error -> {
                    State.Error(
                        it.throwable,
                        it.message,
                        topHeadlinesNewsLocalDataSource.getAllNoFlow()
                    )
                }
                is Resource.Loading -> {
                    State.Loading()
                }
            }
        }
    }

    suspend fun getTopHeadlinesByCategory(category: String): Flow<State<List<TopHeadlinesNews>?>> {
        mapper.setCategory(category)
        return newsNetworkDataSource.getTopHeadlinesByCategory(category).map {
            when (it) {
                is Resource.Success -> {
                    val data = it.data.articles?.map(mapper::map) ?: emptyList()
                    topHeadlinesNewsLocalDataSource.insertAll(data)
                    State.Success(topHeadlinesNewsLocalDataSource.getAllByCategory(category))
                }
                is Resource.Error -> {
                    State.Error(
                        it.throwable,
                        it.message,
                        topHeadlinesNewsLocalDataSource.getAllByCategory(category)
                    )
                }
                is Resource.Loading -> {
                    State.Loading()
                }
            }
        }
    }

    suspend fun getTopHeadlinesByCategoryFlow(category: String) = topHeadlinesNewsLocalDataSource.getAllByCategoryFlow(category)

    suspend fun getByTitle(title: String) = topHeadlinesNewsLocalDataSource.getByTitle(title)

    suspend fun updateIsSaved(isSaved: Boolean, title: String) =
        topHeadlinesNewsLocalDataSource.updateIsSaved(isSaved, title)

    suspend fun isSaved(title: String) = topHeadlinesNewsLocalDataSource.isSaved(title)

    suspend fun getAllSavedNews() = topHeadlinesNewsLocalDataSource.getAllSavedNews()

}