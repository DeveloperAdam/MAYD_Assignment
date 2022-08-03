package com.adam.mayd_assignment.hilt

import com.adam.mayd_assignment.data.ShortlyDataModel
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class ApiServiceHelperImplementation @Inject constructor(
    @Named("APIServiceJSON") private val apiServiceJSON: RetrofitServiceInstance
) : ApiServiceHelper {

    override suspend fun shortenUrl(): Response<ShortlyDataModel> = apiServiceJSON.shortenUrl()
}