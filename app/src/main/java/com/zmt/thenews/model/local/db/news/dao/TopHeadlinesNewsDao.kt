package com.zmt.thenews.model.local.db.news.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.zmt.thenews.model.local.db.helper.BaseDao
import com.zmt.thenews.model.local.db.news.entity.TopHeadlinesNews
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TopHeadlinesNewsDao : BaseDao<TopHeadlinesNews> {
    @Query("select * from topheadlinesnews")
    abstract fun getAll(): Flow<List<TopHeadlinesNews>?>

    @Query("select * from topheadlinesnews where title=:title")
    abstract fun getByTitle(title: String): Flow<TopHeadlinesNews?>

    @Query("select * from topheadlinesnews")
    abstract fun getAllNoFlow(): List<TopHeadlinesNews>?

    @Query("select * from topheadlinesnews where category=:category")
    abstract fun getAllByCategory(category: String): List<TopHeadlinesNews>?

    @Query("select * from topheadlinesnews where category=:category")
    abstract fun getAllByCategoryFlow(category: String): Flow<List<TopHeadlinesNews>?>

    @Query("select * from topheadlinesnews where isSaved= :isSaved")
    abstract fun getAllSavedNewsFlow(isSaved: Boolean = true): Flow<List<TopHeadlinesNews>?>

    @Query("update topheadlinesnews set isSaved=:isSaved where title=:title")
    abstract fun updateIsSaved(isSaved: Boolean, title: String)

    @Query("select isSaved from topheadlinesnews where title=:title")
    abstract fun isSaved(title: String): Boolean?
}
