package com.zmt.thenews.model.local.db.news.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.zmt.thenews.model.remote.response.SourceArticleNetworkData

@Entity
data class TopHeadlinesNews(
    val sourceId: String,
    val sourceName: String,
    val author: String,
    @PrimaryKey
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
    val category: String,
    val isSaved: Boolean ? = null,
)

