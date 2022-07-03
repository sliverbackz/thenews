package com.zmt.thenews.model.local.db.news.dao

import androidx.room.Dao
import androidx.room.Query
import com.zmt.thenews.model.local.db.helper.BaseDao
import com.zmt.thenews.model.local.db.news.entity.NewsSource
import kotlinx.coroutines.flow.Flow

@Dao
abstract class NewsSourceDao : BaseDao<NewsSource> {
    @Query("select * from newssource")
    abstract fun getAll(): Flow<List<NewsSource>?>

    @Query("select * from newssource")
    abstract fun getAllNoFlow(): List<NewsSource>?

    @Query("select * from newssource where id=:id")
    abstract fun getById(id: String): Flow<NewsSource?>

}
