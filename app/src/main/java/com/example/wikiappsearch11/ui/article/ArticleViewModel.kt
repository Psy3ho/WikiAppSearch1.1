package com.example.wikiappsearch11.ui.article

import android.arch.lifecycle.MutableLiveData
import com.example.wikiappsearch11.base.BaseViewModel
import com.example.wikiappsearch11.model.Article


class ArticleViewModel: BaseViewModel() {
    private val postTitle = MutableLiveData<String>()
    private val postBody = MutableLiveData<String>()

    fun bind(article: Article){
        postTitle.value = article.title
        postBody.value = article.snippet
    }

    fun getArticleTitle():MutableLiveData<String>{
        return postTitle
    }

    fun getArticleBody():MutableLiveData<String>{
        return postBody
    }
}