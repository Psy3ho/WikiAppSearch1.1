package com.example.wikiappsearch11.ui.article

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.wikiappsearch11.R
import com.example.wikiappsearch11.databinding.ArticleItemBinding
import com.example.wikiappsearch11.model.Article
import com.example.wikiappsearch11.model.Data

class ArticleListAdapter: RecyclerView.Adapter<ArticleListAdapter.ViewHolder>() {
    private lateinit var articleList:List<Article>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleListAdapter.ViewHolder {
        val binding: ArticleItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.article_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleListAdapter.ViewHolder, position: Int) {
        holder.bind(articleList[position])
    }

    override fun getItemCount(): Int {
        return if(::articleList.isInitialized) articleList.size else 0
    }

    fun updatePostList(postList: Data){
        this.articleList = postList.query.search
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ArticleItemBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = ArticleViewModel()
        fun bind(article:Article){
            viewModel.bind(article)
            binding.viewModel = viewModel
        }
    }
}