package com.example.giphy.domain.repository

import com.example.giphy.ResponseResult
import com.example.giphy.domain.models.GifModel

interface GifRepository {
    suspend fun getGifs() : ResponseResult<List<GifModel>>
}