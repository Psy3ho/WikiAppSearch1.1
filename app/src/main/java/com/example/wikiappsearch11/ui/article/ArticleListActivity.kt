package com.example.wikiappsearch11.ui.article

import android.app.SearchManager
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import com.example.wikiappsearch11.R
import com.example.wikiappsearch11.databinding.AcitivityArticleListBinding

class ArticleListActivity: AppCompatActivity() {
    private lateinit var binding: AcitivityArticleListBinding

    private lateinit var viewModel: ArticleListViewModel

    private var errorSnackbar: Snackbar? = null

    private var searchView: SearchView? = null


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.acitivity_article_list)
        binding.postList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProviders.of(this).get(ArticleListViewModel::class.java)

        viewModel.errorMessage.observe(this, Observer {
                errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        // Associate search_layout configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.search)
            .actionView as SearchView
        searchView!!.setSearchableInfo(searchManager
            .getSearchableInfo(componentName))
        searchView!!.maxWidth = Integer.MAX_VALUE

        // listening to search query text change
        searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // filter recycler view when query submitted
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                // filter recycler view when text is changed
                Log.d("search:", query)
                viewModel.loadPosts(query)
                return false
            }
        })
        return true
    }



    private fun showError(@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }
}