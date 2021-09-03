package com.comnet.test

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class News(
    @SerializedName("published_date")
    val publishedDate: String,
    val thumbnail: String,
    val title: String,
    val content:String
):Parcelable{

}