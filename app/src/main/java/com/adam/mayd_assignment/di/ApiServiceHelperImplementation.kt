package com.adam.mayd_assignment.di

import com.adam.mayd_assignment.data.ShortlyDataModel
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class ApiServiceHelperImplementation @Inject constructor(
    @Named("APIServiceJSON") private val apiServiceJSON: RetrofitServiceInstance
) : ApiServiceHelper {

    override suspend fun shortenUrl(url : String): Response<ShortlyDataModel> = apiServiceJSON.shortenUrl(url)
}