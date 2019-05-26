package com.example.wikiappsearch11.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
@Entity
data class Article(@field:PrimaryKey val id: Int, val title: String, val snippet: String, val information: String, val language: String)
data class Data(val query: Children)
data class Children(val search: List<Article>)