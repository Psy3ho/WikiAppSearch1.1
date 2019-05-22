package com.example.wikiappsearch11

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.wikiappsearch11.model.Article

class MainActivity : AppCompatActivity() {

    var article:  Article? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}
