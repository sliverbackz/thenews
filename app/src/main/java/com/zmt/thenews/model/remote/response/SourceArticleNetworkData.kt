package com.zmt.thenews.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SourceArticleNetworkData(
    @Json(name = "id") val id: String?,
    @Json(name = "name") val name: String?
)
