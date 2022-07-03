package com.zmt.thenews.model.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zmt.thenews.model.local.db.news.dao.AllNewsDao
import com.zmt.thenews.model.local.db.news.dao.NewsSourceDao
import com.zmt.thenews.model.local.db.news.dao.TopHeadlinesNewsDao
import com.zmt.thenews.model.local.db.news.entity.AllNews
import com.zmt.thenews.model.local.db.news.entity.NewsSource
import com.zmt.thenews.model.local.db.news.entity.TopHeadlinesNews

@Database(
    entities = [NewsSource::class, AllNews::class, TopHeadlinesNews::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsSourceDao(): NewsSourceDao

    abstract fun allNewsDao(): AllNewsDao

    abstract fun topHeadlinesNewsDao(): TopHeadlinesNewsDao
}