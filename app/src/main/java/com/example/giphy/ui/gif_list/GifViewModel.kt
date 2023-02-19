package com.example.giphy.ui.gif_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.giphy.ResponseResult
import com.example.giphy.domain.models.GifModel
import com.example.giphy.domain.usecases.GetGifListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GifViewModel @Inject constructor(
    private val getGifListUseCase: GetGifListUseCase
): ViewModel() {

    private val _gifList = MutableLiveData<ResponseResult<List<GifModel>>>()
    val gifList: LiveData<ResponseResult<List<GifModel>>>get() = _gifList

    init {
        getGifList()
    }

    private fun getGifList() {
        viewModelScope.launch {
            _gifList.value = getGifListUseCase.execute()
        }
    }

    fun reloadGifs() {
        getGifList()
    }
}