package com.gourav.app.ui.cars

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.gourav.app.R
import com.gourav.app.databinding.ActivityArticleBinding
import com.gourav.app.model.Article


class ArticleActivity: AppCompatActivity() {
    private lateinit var binding: ActivityArticleBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

          intent?.extras?.apply {
              if(containsKey("NEWSDATA")){
                 val mArticle =  getParcelable<Article>("NEWSDATA")
                  binding = DataBindingUtil.setContentView(this@ArticleActivity, R.layout.activity_article)
                  binding.mData = mArticle
              }
          }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                supportFinishAfterTransition()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}