package com.adam.mayd_assignment.mvvm

import com.adam.mayd_assignment.R
import com.adam.mayd_assignment.data.ShortlyDataModel
import com.adam.mayd_assignment.di.ApiServiceHelperImplementation
import com.adam.mayd_assignment.utils.DataState
import com.adam.mayd_assignment.utils.MAYD_ApplicationClass
import com.adam.mayd_assignment.utils.NetworkHelper
import com.adam.mayd_assignment.utils.ShortlyConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.HttpURLConnection
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShortlyRepository @Inject constructor(
    private val context: MAYD_ApplicationClass,
    private val networkHelper: NetworkHelper,
    private val apiServiceHelper: ApiServiceHelperImplementation
){

    suspend fun shortenUrl(urlToShorten :String) : Flow<DataState<ShortlyDataModel>> = flow {
        try {

            if (!networkHelper.hasActiveInternetConnection()) {
                emit(DataState.NetworkError(context.getString(R.string.timeout_error)))
                return@flow
            }

            //Initiate
            emit(DataState.Loading)
            val url = "shorten?url=$urlToShorten"
            val response = apiServiceHelper.shortenUrl(url)
            if (response.isSuccessful){
                when(response.code()) {

                    HttpURLConnection.HTTP_OK -> { emit(DataState.Success(response.body())) }

                    HttpURLConnection.HTTP_INTERNAL_ERROR -> emit(DataState.Error(context.getString(R.string.generic_error)))
                    else ->{
                        emit(DataState.Error(response.message() ?: context.getString(R.string.generic_error)))
                    }
                }
            }

        }
        catch (e: Exception)
        {
            emit(DataState.Error(context.getString(R.string.generic_error)))
            e.printStackTrace()
        }
    }
}