package com.example.wikiappsearch11.network

import com.example.wikiappsearch11.model.Data
import io.reactivex.Observable
import retrofit2.http.GET

interface ArticleApi {
    @GET("api.php?action=query&list=search&utf8=&format=json&srsearch=ahoj")
    fun getArticles(): Observable<Data>
}