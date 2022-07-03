package com.zmt.thenews.repository

import com.zmt.thenews.helper.State
import com.zmt.thenews.model.Params
import com.zmt.thenews.model.local.datasource.AllNewsLocalDataSource
import com.zmt.thenews.model.local.db.news.entity.AllNews
import com.zmt.thenews.model.remote.datasource.NewsNetworkDataSource
import com.zmt.thenews.model.remote.helper.Resource
import com.zmt.thenews.repository.mapper.AllNewsMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AllNewsRepository @Inject constructor(
    private val newsNetworkDataSource: NewsNetworkDataSource,
    private val allNewsLocalDataSource: AllNewsLocalDataSource
) {
    private val mapper = AllNewsMapper()

    suspend fun getEverythingNews(params: Params): Flow<State<List<AllNews>?>> {
        return newsNetworkDataSource.getTopHeadlines(params).map {
            when (it) {
                is Resource.Success -> {
                    val data = it.data.articles?.map(mapper::map) ?: emptyList()
                    allNewsLocalDataSource.insertAll(data)
                    State.Success(allNewsLocalDataSource.getAllNoFlow())
                }
                is Resource.Error -> {
                    State.Error(it.throwable, it.message,allNewsLocalDataSource.getAllNoFlow())
                }
                is Resource.Loading -> {
                    State.Loading()
                }
            }
        }
    }
}