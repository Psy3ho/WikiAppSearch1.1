
package com.example.wikiappsearch11.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface ArticleDao {
    @get:Query("SELECT * FROM article")
    val all: List<Article>

    @Insert
    fun insertAll(vararg articles: Article)
}