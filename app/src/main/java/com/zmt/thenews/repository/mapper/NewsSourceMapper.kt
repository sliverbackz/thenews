package com.zmt.thenews.repository.mapper

import com.zmt.thenews.model.local.db.news.entity.NewsSource
import com.zmt.thenews.model.remote.response.SourceNetworkData

class NewsSourceMapper : UnidirectionalMap<SourceNetworkData, NewsSource> {
    override fun map(item: SourceNetworkData): NewsSource {
        return NewsSource(
            id = item.id ?: "",
            name = item.name ?: "",
            description = item.description ?: "",
            url = item.url ?: "",
            category = item.category ?: "",
            language = item.language ?: "",
            country = item.country ?: ""
        )
    }
}