package com.ImageSearch.model

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import timber.log.Timber

object SharedPreferencesUtil {

    private const val DEFAULT_IMAGE_URL = "image_url"
    private const val LIKED_ITEMS_KEY = "likedItems"

    fun saveLikedItems(likedItems: List<Item>, context: Context) {
        val gson = Gson()
        val likedItemsJson = gson.toJson(likedItems)

        val spf = context.getSharedPreferences(DEFAULT_IMAGE_URL, Context.MODE_PRIVATE)
        val editor = spf.edit()

        editor.putString(LIKED_ITEMS_KEY, likedItemsJson)
        editor.apply()

        Timber.e("data saved ")
    }

    fun loadLikedItems(context: Context): List<Item> {
        val spf = context.getSharedPreferences(DEFAULT_IMAGE_URL, Context.MODE_PRIVATE)
        val likedItemsJson = spf.getString(LIKED_ITEMS_KEY, "")

        if (likedItemsJson.isNullOrEmpty()) {
            return emptyList()
        }

        val gson = Gson()
        val likedItemsType = object : TypeToken<List<Item>>() {}.type
        return gson.fromJson(likedItemsJson, likedItemsType)

        Timber.e("data load")
    }
}


