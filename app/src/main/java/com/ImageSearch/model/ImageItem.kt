package com.ImageSearch.model

import com.google.gson.annotations.SerializedName

data class ImageItem(
    @SerializedName("display_sitename")
    val thumbnail_url:String,
    val image_url:String,
    val sitename:String,
    val datetime:String
)
