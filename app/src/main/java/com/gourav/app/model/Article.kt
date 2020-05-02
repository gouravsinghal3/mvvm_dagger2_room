package com.gourav.app.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.gourav.app.utils.getFormatedDate
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Article(
    val author: String?,
    val content: String?,
    val description: String,
    val publishedAt: String,
    val title: String,
    val url: String?,
    val urlToImage: String
) : Parcelable
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    val date : String
        get() {
           return getFormatedDate(publishedAt)
        }
}