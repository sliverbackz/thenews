package com.zmt.thenews.model.local.db.news.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AllNews(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val sourceId: String,
    val sourceName: String,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)