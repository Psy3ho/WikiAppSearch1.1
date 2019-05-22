package com.example.wikiappsearch11.base

import android.arch.lifecycle.ViewModel
import com.example.wikiappsearch11.injection.module.NetworkModule
import com.example.wikiappsearch11.injection.module.component.DaggerViewModelInjector
import com.example.wikiappsearch11.injection.module.component.ViewModelInjector
import com.example.wikiappsearch11.ui.ArticleListViewModel

abstract class BaseViewModel: ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is ArticleListViewModel -> injector.inject(this)
        }
    }
}