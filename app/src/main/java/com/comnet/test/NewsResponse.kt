package com.comnet.test

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("cards")
    val news: List<News>
)