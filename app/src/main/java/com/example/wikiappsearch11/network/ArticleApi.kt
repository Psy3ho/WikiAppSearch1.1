package com.example.wikiappsearch11.network

import com.example.wikiappsearch11.model.Data
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleApi {
    @GET("api.php?action=query&list=search&utf8=&format=json")
    fun getArticles(@Query("sroffset") sroffset: String,@Query("srlimit") srlimit: String, @Query("srsearch") srsearch: String): Observable<Data>
}