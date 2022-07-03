package com.zmt.thenews.model.remote.service

import com.zmt.thenews.model.remote.response.NewsResponse
import com.zmt.thenews.model.remote.response.SourceResponse
import com.zmt.thenews.model.remote.response.TopHeadlineResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("q") query: String? = null,
        @Query("searchIn") searchIn: String = "title,description",
        @Query("sources") sources: List<String>? = null,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
//        @Query("language") language: String="en"
    ): Response<TopHeadlineResponse>

    @GET("v2/top-headlines")
    suspend fun getTopHeadlinesByCategory(
        @Query("category") category: String,
        @Query("language") language: String,
        @Query("country") country: String,
    ): Response<TopHeadlineResponse>

    @GET("v2/everything")
    suspend fun getEverythingNews(
        @Query("q") query: String,
        @Query("searchIn") searchIn: String = "title,description",
        @Query("sources") sources: List<String>,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
//        @Query("language") language: String,
//        @Query("sortBy") sortBy: String,
    ): Response<NewsResponse>


    @GET("v2/sources")
    suspend fun getAllSources(): Response<SourceResponse>
}

