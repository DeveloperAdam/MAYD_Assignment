package com.adam.mayd_assignment.hilt

import com.adam.mayd_assignment.data.ShortlyDataModel
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitServiceInstance {

    @GET
    suspend fun shortenUrl(): Response<ShortlyDataModel>
}