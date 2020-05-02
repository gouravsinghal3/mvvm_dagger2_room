package com.gourav.app.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.gourav.app.model.Article
import com.gourav.app.model.ArticleDao

@Database(entities = [Article::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}