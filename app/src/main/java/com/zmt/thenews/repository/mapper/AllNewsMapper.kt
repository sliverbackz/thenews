package com.zmt.thenews.repository.mapper

import com.zmt.thenews.model.local.db.news.entity.AllNews
import com.zmt.thenews.model.remote.response.ArticleNetworkData

class AllNewsMapper : UnidirectionalMap<ArticleNetworkData, AllNews> {
    override fun map(item: ArticleNetworkData): AllNews {
        return AllNews(
            sourceId = item.sourceArticle.id ?: "",
            sourceName = item.sourceArticle.name ?: "",
            author = item.author ?: "",
            title = item.title ?: "",
            description = item.description ?: "",
            url = item.url ?: "",
            urlToImage = item.urlToImage ?: "",
            publishedAt = item.publishedAt ?: "",
            content = item.content ?: ""
        )
    }
}