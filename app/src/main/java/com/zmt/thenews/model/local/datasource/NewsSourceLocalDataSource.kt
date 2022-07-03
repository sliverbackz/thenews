package com.zmt.thenews.model.local.datasource

import com.zmt.thenews.model.local.db.news.dao.NewsSourceDao
import com.zmt.thenews.model.local.db.news.entity.NewsSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsSourceLocalDataSource @Inject constructor(
    private val newsSourceDao: NewsSourceDao
) {
    suspend fun insertAll(data: List<NewsSource>) {
        newsSourceDao.insert(data)
    }

    suspend fun getAll(): Flow<List<NewsSource>?> {
        return newsSourceDao.getAll()
    }

    suspend fun getAllNoFlow()= newsSourceDao.getAllNoFlow()

    suspend fun getById(id: String): Flow<NewsSource?> {
        return newsSourceDao.getById(id)
    }
}