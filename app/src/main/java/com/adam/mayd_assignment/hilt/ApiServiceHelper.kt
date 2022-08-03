package com.adam.mayd_assignment.hilt

import com.adam.mayd_assignment.data.ShortlyDataModel
import retrofit2.Response
import javax.inject.Singleton

@Singleton
interface ApiServiceHelper {

    suspend fun shortenUrl() : Response<ShortlyDataModel>
}