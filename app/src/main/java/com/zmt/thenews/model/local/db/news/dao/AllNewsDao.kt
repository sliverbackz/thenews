package com.zmt.thenews.model.local.db.news.dao

import androidx.room.Dao
import androidx.room.Query
import com.zmt.thenews.model.local.db.helper.BaseDao
import com.zmt.thenews.model.local.db.news.entity.AllNews
import com.zmt.thenews.model.local.db.news.entity.TopHeadlinesNews
import kotlinx.coroutines.flow.Flow

@Dao
abstract class AllNewsDao : BaseDao<AllNews> {
    @Query("select * from allnews")
    abstract fun getAll(): Flow<List<AllNews>?>

    @Query("select * from allnews")
    abstract fun getAllNoFlow(): List<AllNews>?

    @Query("select * from allnews where sourceId=:sourceId")
    abstract fun getById(sourceId: String): Flow<AllNews?>
}
