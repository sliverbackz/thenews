package com.zmt.thenews.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TopHeadlineResponse(
    @Json(name = "status") val status: String,
    @Json(name = "totalResults") val totalResults: Int,
    @Json(name = "articles") val articles: List<ArticleNetworkData>?
)


