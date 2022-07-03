package com.zmt.thenews.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SourceNetworkData(
    @Json(name = "id") val id: String?,
    @Json(name = "name") val name: String?,
    @Json(name = "description") val description: String?,
    @Json(name = "url") val url: String?,
    @Json(name = "category") val category: String?,
    @Json(name = "language") val language: String?,
    @Json(name = "country") val country: String?
)

//"id": "wired-de",
//"name": "Wired.de",
//"description": "Wired reports on how emerging technologies affect culture, the economy and politics.",
//"url": "https://www.wired.de",
//"category": "technology",
//"language": "de",
//"country": "de"