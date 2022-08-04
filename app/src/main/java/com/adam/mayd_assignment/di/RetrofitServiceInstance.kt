package com.adam.mayd_assignment.di

import com.adam.mayd_assignment.data.ShortlyDataModel
import retrofit2.Response
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface RetrofitServiceInstance {

    @GET
    suspend fun shortenUrl(@Url url: String): Response<ShortlyDataModel>
}