package com.gourav.app.network

import com.gourav.app.model.ArticleDataModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * The interface which provides methods to get result of webservices
 */
interface ArticleApi {
    /**
     * Get the list of the Article from the API
     */
    @GET("top-headlines")
    fun getArticles(@QueryMap queryParams: HashMap<String, Any>): Observable<ArticleDataModel>
}