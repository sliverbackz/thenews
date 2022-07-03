package com.zmt.thenews.model

data class Params(
    val query: String,
    val sources: List<String>,
    val page: Int,
    val pageSize: Int
)
