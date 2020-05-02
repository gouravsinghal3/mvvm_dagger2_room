package com.gourav.app.ui.cars

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.view.View
import com.gourav.app.R
import com.gourav.app.base.BaseViewModel
import com.gourav.app.model.Article
import com.gourav.app.model.ArticleDao
import com.gourav.app.network.ArticleApi
import com.gourav.app.utils.isOnline
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ArticleListViewModel(private val articleDao: ArticleDao): BaseViewModel(){
    @Inject
    lateinit var articleApi: ArticleApi
    val articleListAdapter: ArticleListAdapter = ArticleListAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener {
        loadArticles(hashMap) }
    private val hashMap = HashMap<String, Any>()

    private lateinit var subscription: Disposable

    init{
        hashMap["country"] = "us"
        hashMap["apiKey"] = "f18dadc3e5614d5db369ac10166447f2"
        loadArticles(hashMap)
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }


    private fun loadArticles(hashMap: HashMap<String, Any>) {
        subscription = Observable.fromCallable { articleDao.all }
            .concatMap {
                    dbArticleList ->
                if(dbArticleList.isEmpty())
                    articleApi.getArticles(hashMap).concatMap {
                            apiArticleResponse -> articleDao.insertAll( apiArticleResponse.articles)
                        Observable.just(apiArticleResponse.articles)
                    }
                else
                    Observable.just(dbArticleList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveArticleListStart() }
            .doOnTerminate { onRetrieveArticleListFinish() }
            .subscribe(
                { result -> onRetrieveArticleListSuccess(result as List<Article>) },
                { onRetrieveArticleListError() }
            )
    }

    private fun onRetrieveArticleListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveArticleListFinish(){
        loadingVisibility.value = View.GONE
    }


    private fun onRetrieveArticleListSuccess(articleList:List<Article>){


        articleListAdapter.updateArticleList(articleList)
    }

    private fun onRetrieveArticleListError(){
        errorMessage.value = R.string.post_error
    }
}