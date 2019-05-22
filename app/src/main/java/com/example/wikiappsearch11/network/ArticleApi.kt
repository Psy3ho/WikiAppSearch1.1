package com.example.wikiappsearch11.network

import com.example.wikiappsearch11.model.Article
import io.reactivex.Observable
import retrofit2.http.GET

interface ArticleApi {
    @GET("/articles")
    fun getArticles(): Observable<List<Article>>
}