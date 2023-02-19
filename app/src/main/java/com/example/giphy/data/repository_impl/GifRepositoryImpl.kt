package com.example.giphy.data.repository_impl

import com.example.giphy.ResponseResult
import com.example.giphy.data.mappers.GifMapper
import com.example.giphy.data.network.GifApiService
import com.example.giphy.domain.models.GifModel
import com.example.giphy.domain.repository.GifRepository

class GifRepositoryImpl(
    private val gifApiService: GifApiService,
    private val gifMapper: GifMapper
): GifRepository {

    override suspend fun getGifs(): ResponseResult<List<GifModel>> {
        return try {
            val response = gifApiService.getGifs()
            if (response.isSuccessful) {
                response.body().let {
                    return@let ResponseResult.Success(gifMapper.mapApiGifsToModelList(it))
                }
            } else {
                ResponseResult.Failure(message = "Response is not successful")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseResult.Failure(message = "Couldn't reach the server. Check your internet connection")
        }
    }
}