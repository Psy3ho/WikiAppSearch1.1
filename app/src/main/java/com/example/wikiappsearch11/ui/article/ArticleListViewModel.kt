package com.example.wikiappsearch11.ui.article

import android.arch.lifecycle.MutableLiveData
import android.util.Log.*
import android.view.View
import com.example.wikiappsearch11.R
import com.example.wikiappsearch11.base.BaseViewModel
import com.example.wikiappsearch11.model.Article
import com.example.wikiappsearch11.model.Data
import com.example.wikiappsearch11.network.ArticleApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ArticleListViewModel: BaseViewModel(){
    @Inject
    lateinit var articleApi: ArticleApi

    private lateinit var subscription: Disposable

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }


    val articleListAdapter: ArticleListAdapter = ArticleListAdapter()

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
                { result ->
                    d("chyba = ", result.toString())
                    onRetrieveArticleListSuccess(result)
                },
                { error -> onRetrieveArticleListError(error.toString()) }
            )
    }

    private fun onRetrieveArticleListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveArticleListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveArticleListSuccess(articleList: Data){
        articleListAdapter.updatePostList(articleList)
    }

    private fun onRetrieveArticleListError(error: String){
        d("chyba = ", error)
        errorMessage.value = R.string.post_error
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}