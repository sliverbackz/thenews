package com.zmt.thenews.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SourceResponse(
    @Json(name = "status") val status: String?,
    @Json(name = "sources") val sources: List<SourceNetworkData>?
)