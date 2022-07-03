package com.zmt.thenews.model.local.datasource

import com.zmt.thenews.model.local.db.news.dao.AllNewsDao
import com.zmt.thenews.model.local.db.news.dao.TopHeadlinesNewsDao
import com.zmt.thenews.model.local.db.news.entity.AllNews
import com.zmt.thenews.model.local.db.news.entity.TopHeadlinesNews
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AllNewsLocalDataSource @Inject constructor(
    private val allNewsDao: AllNewsDao
) {
    suspend fun insertAll(data: List<AllNews>) {
        allNewsDao.insert(data)
    }

    suspend fun getAll(): Flow<List<AllNews>?> {
        return allNewsDao.getAll()
    }

    suspend fun getById(id: String): Flow<AllNews?> {
        return allNewsDao.getById(id)
    }

    suspend fun getAllNoFlow() = allNewsDao.getAllNoFlow()
}