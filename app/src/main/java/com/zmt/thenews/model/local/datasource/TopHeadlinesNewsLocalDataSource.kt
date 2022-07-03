package com.zmt.thenews.model.local.datasource

import com.zmt.thenews.model.local.db.news.dao.TopHeadlinesNewsDao
import com.zmt.thenews.model.local.db.news.entity.TopHeadlinesNews
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopHeadlinesNewsLocalDataSource @Inject constructor(
    private val topHeadlinesNewsDao: TopHeadlinesNewsDao
) {
    suspend fun insertAll(data: List<TopHeadlinesNews>) {
        topHeadlinesNewsDao.insert(data)
    }

    suspend fun getAll(): Flow<List<TopHeadlinesNews>?> {
        return topHeadlinesNewsDao.getAll()
    }

    suspend fun getByTitle(id: String): Flow<TopHeadlinesNews?> {
        return topHeadlinesNewsDao.getByTitle(id)
    }

    suspend fun getAllNoFlow() = topHeadlinesNewsDao.getAllNoFlow()

    suspend fun getAllByCategory(category: String) = topHeadlinesNewsDao.getAllByCategory(category)

    suspend fun getAllSavedNews() = topHeadlinesNewsDao.getAllSavedNewsFlow()

    suspend fun getAllByCategoryFlow(category: String) =
        topHeadlinesNewsDao.getAllByCategoryFlow(category)

    suspend fun updateIsSaved(isSaved: Boolean, title: String) =
        topHeadlinesNewsDao.updateIsSaved(isSaved, title)

    suspend fun isSaved(title: String) = topHeadlinesNewsDao.isSaved(title)
}