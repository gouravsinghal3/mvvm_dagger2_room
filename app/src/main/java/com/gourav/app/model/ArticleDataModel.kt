package com.gourav.app.model

import com.google.gson.annotations.SerializedName

data class ArticleDataModel(
    @SerializedName("article")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    val totalResults: Int
)