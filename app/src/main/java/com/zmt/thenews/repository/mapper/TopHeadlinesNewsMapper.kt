package com.zmt.thenews.repository.mapper

import com.zmt.thenews.model.local.db.news.entity.TopHeadlinesNews
import com.zmt.thenews.model.remote.response.ArticleNetworkData
import timber.log.Timber

class TopHeadlinesNewsMapper : UnidirectionalMap<ArticleNetworkData, TopHeadlinesNews> {
    private var category: String = ""
    override fun map(item: ArticleNetworkData): TopHeadlinesNews {
        return TopHeadlinesNews(
            sourceId = item.sourceArticle.id ?: "",
            sourceName = item.sourceArticle.name ?: "",
            author = item.author ?: "",
            title = item.title ?: "",
            description = item.description ?: "",
            url = item.url ?: "",
            urlToImage = item.urlToImage ?: "",
            publishedAt = item.publishedAt ?: "",
            content = item.content ?: "",
            category = category
        )
    }

    fun setCategory(category: String) {
        this.category = category
    }
}