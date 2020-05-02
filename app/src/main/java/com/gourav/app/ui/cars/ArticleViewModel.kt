package com.gourav.app.ui.cars

import android.app.Activity
import android.arch.lifecycle.MutableLiveData
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.view.View
import com.gourav.app.base.BaseViewModel
import com.gourav.app.model.Article
import com.gourav.app.utils.extension.getParentActivity
import com.gourav.app.utils.getFormatedDate


class ArticleViewModel: BaseViewModel() {
    private val title = MutableLiveData<String>()
    private val ingress = MutableLiveData<String>()
    private  val image = MutableLiveData<String>()
    private  val date = MutableLiveData<String>()
    private lateinit var mArticle : Article


    fun bind(article: Article){
        title.value = article.title
        ingress.value = article.description
        image.value = article.urlToImage
        date.value = article.publishedAt
        mArticle = article
    }

    fun getTitle():MutableLiveData<String>{
        return title
    }

    fun getIngress():MutableLiveData<String>{
        return ingress
    }

    fun getImage():MutableLiveData<String>{
        return image
    }

    fun getDate():MutableLiveData<String>{

        val modifiedDate = getFormatedDate(date.value.toString())
        date.value = modifiedDate;

        return date
    }

    fun callActivity(view : View)
    {
          val intent = Intent(view.getParentActivity(), ArticleActivity::class.java)
          val bundle = Bundle()
              bundle.putParcelable("NEWSDATA", mArticle)
        intent.putExtras(bundle)

        val options: ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
            view.getParentActivity() as Activity,
            view,
            "mainImg"
        )
        view.context.startActivity(intent, options.toBundle())
    }
}