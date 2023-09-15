package com.ImageSearch.model

import com.google.gson.annotations.SerializedName

data class ImageSearchResponse(
    @SerializedName("meta")
    val metaDate:MetaData?,

    @SerializedName("documents")
    var documents: MutableList<ImageItem>?
)
