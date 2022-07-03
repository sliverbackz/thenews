package com.zmt.thenews.model.local.db.news.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.zmt.thenews.model.remote.response.SourceArticleNetworkData

@Entity
data class SavedNews(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 1,
    val title: String
)

