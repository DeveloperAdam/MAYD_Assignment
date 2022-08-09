package com.adam.mayd_assignment.di

import com.adam.mayd_assignment.data.ShortlyDataModel
import retrofit2.Response
import retrofit2.http.*

interface RetrofitServiceInstance {

    @GET("shorten")
    suspend fun shortenUrl(@Query("url") url: String): Response<ShortlyDataModel>
}