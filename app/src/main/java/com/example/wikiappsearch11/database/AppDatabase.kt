package com.example.wikiappsearch11.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.wikiappsearch11.model.Article
import com.example.wikiappsearch11.model.ArticleDao


@Database(entities = arrayOf(Article::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}