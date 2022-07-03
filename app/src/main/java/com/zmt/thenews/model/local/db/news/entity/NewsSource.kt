package com.zmt.thenews.model.local.db.news.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsSource(
    @PrimaryKey(autoGenerate = true)
    val InId: Int = 0,
    val id: String,
    val name: String,
    val description: String,
    val url: String,
    val category: String,
    val language: String,
    val country: String
)
