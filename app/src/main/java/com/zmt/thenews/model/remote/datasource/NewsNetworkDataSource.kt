package com.zmt.thenews.model.remote.datasource

import com.zmt.thenews.model.Params
import com.zmt.thenews.model.remote.helper.BaseDataSource
import com.zmt.thenews.model.remote.helper.Resource
import com.zmt.thenews.model.remote.response.NewsResponse
import com.zmt.thenews.model.remote.response.SourceResponse
import com.zmt.thenews.model.remote.response.TopHeadlineResponse
import com.zmt.thenews.model.remote.service.NewsService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsNetworkDataSource @Inject constructor(
    private val newsService: NewsService
) : BaseDataSource() {
    suspend fun getEverythingNews(params: Params): Flow<Resource<NewsResponse>> {
        return flow {
            emit(Resource.Loading())
            emit(getResultOrError {
                newsService.getEverythingNews(
                    query = params.query,
                    sources = params.sources,
                    page = params.page,
                    pageSize = params.pageSize
                )
            })
        }
    }

    suspend fun getTopHeadlines(params: Params): Flow<Resource<TopHeadlineResponse>> {
        return flow {
            emit(Resource.Loading())
            emit(getResultOrError {
                newsService.getTopHeadlines(
                    query = params.query,
                    sources = params.sources,
                    page = params.page,
                    pageSize = params.pageSize
                )
            })
        }
    }

    suspend fun getTopHeadlinesByCategory(category: String): Flow<Resource<TopHeadlineResponse>> {
        return flow {
            val takeIfUS =  if(category == "us") "us" else ""
            emit(Resource.Loading())
            emit(getResultOrError {
                newsService.getTopHeadlinesByCategory(
                    category =  if(category == "us") "" else category,
                    language = "en",
                    country = takeIfUS
                )
            })
        }
    }

    suspend fun getAllSources(): Flow<Resource<SourceResponse>> {
        return flow {
            emit(Resource.Loading())
            emit(getResultOrError { newsService.getAllSources() })
        }
    }


}