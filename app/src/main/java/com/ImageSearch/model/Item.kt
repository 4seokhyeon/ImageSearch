package com.ImageSearch.model

import android.content.Context
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import timber.log.Timber

data class Item(

    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,
    val image_url: String,
    val display_sitename: String,
    val datetime: String,

    var like: Boolean = false
)
