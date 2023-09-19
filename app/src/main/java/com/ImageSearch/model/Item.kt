package com.ImageSearch.model

import com.google.gson.annotations.SerializedName

data class Item(

    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,
    val image_url: String,
    val display_sitename: String,
    val datetime: String,

    var like: Boolean = false
)
