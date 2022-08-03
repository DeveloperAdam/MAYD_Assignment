package com.adam.mayd_assignment.utils

sealed class DataState<out R> {

    object Loading : DataState<Nothing>()
    data class Success<out T>(val data: T?) : DataState<T>()
    data class Error(val message: String) : DataState<Nothing>()
    data class NetworkError(val message: String) : DataState<Nothing>()
}

data class ConnectionTimeOut(val message: String)

enum class ErrorList {

    noURLSpecified,
    invalidURL,
    rateLimitReached,
    ipBlocked,
    inUse,
    unknownError,
    noCodeSpecified,
    invalidCode,
    missingRequiredFields,
    dissallowLink
}
