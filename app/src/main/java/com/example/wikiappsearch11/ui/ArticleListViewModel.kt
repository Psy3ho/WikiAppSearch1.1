package com.example.wikiappsearch11.ui

import com.example.wikiappsearch11.base.BaseViewModel
import com.example.wikiappsearch11.network.ArticleApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ArticleListViewModel: BaseViewModel(){
    @Inject
    lateinit var articleApi: ArticleApi

    private lateinit var subscription: Disposable

    init{
        loadPosts()
    }

    private fun loadPosts(){
        subscription = articleApi.getArticles()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveArticleListStart() }
            .doOnTerminate { onRetrieveArticleListFinish() }
            .subscribe(
                { onRetrieveArticleListSuccess() },
                { onRetrieveArticleListError() }
            )
    }

    private fun onRetrieveArticleListStart(){

    }

    private fun onRetrieveArticleListFinish(){

    }

    private fun onRetrieveArticleListSuccess(){

    }

    private fun onRetrieveArticleListError(){

    }
}