package com.adam.mayd_assignment.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adam.mayd_assignment.data.ShortlyDataModel
import com.adam.mayd_assignment.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShortlyViewModel @Inject constructor(
    private val repository: ShortlyRepository
) : ViewModel(){

    private val _mutableData = MutableLiveData<DataState<ShortlyDataModel>>()

    val liveData: LiveData<DataState<ShortlyDataModel>>
        get() = _mutableData


    fun getShortUrl(url: String) {
        viewModelScope.launch {
            fetchShortUrl {
                repository.shortenUrl(urlToShorten = url)
                    .flowOn(Dispatchers.IO)
            }
        }
    }

    private fun fetchShortUrl(block: suspend () -> Flow<DataState<ShortlyDataModel>>) {
        viewModelScope.launch {
            try {
                val data = block()
                data.map {
                    _mutableData.value = it
                }.launchIn(viewModelScope)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()

        viewModelScope.cancel()
    }

}