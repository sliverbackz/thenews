package com.zmt.thenews.repository

import com.zmt.thenews.helper.State
import com.zmt.thenews.model.Params
import com.zmt.thenews.model.local.datasource.NewsSourceLocalDataSource
import com.zmt.thenews.model.local.db.news.entity.NewsSource
import com.zmt.thenews.model.remote.datasource.NewsNetworkDataSource
import com.zmt.thenews.model.remote.helper.Resource
import com.zmt.thenews.repository.mapper.NewsSourceMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class NewsSourceRepository @Inject constructor(
    private val newsNetworkDataSource: NewsNetworkDataSource,
    private val newsSourceLocalDataSource: NewsSourceLocalDataSource
) {
    private val newsSourceMapper = NewsSourceMapper()

    suspend fun fetchAllSources(): Flow<State<List<NewsSource>>> {
        return newsNetworkDataSource.getAllSources().map {
            when (it) {
                is Resource.Success -> {
                    val data: List<NewsSource> =
                        it.data.sources?.map(newsSourceMapper::map) ?: emptyList()
                    newsSourceLocalDataSource.insertAll(data)
                    Timber.i("inserted %d", data.size)
                    State.Success(data)
                }
                is Resource.Error -> {
                    State.Error(
                        it.throwable, it.message, newsSourceLocalDataSource.getAllNoFlow()
                    )
                }
                else -> {
                    State.Loading()
                }
            }
        }
    }

    suspend fun getAllSources() = newsSourceLocalDataSource.getAll()


}