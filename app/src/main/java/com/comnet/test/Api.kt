package com.comnet.test

import retrofit2.http.GET

interface Api {
    @GET("app-sample/sample.json")
    suspend fun getNews():NewsResponse
}