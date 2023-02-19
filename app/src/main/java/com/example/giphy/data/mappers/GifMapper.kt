package com.example.giphy.data.mappers

import com.example.giphy.data.api_models.GifApiModel
import com.example.giphy.domain.models.GifModel

class GifMapper {
    fun mapApiGifsToModelList(gifApiModel: GifApiModel?): List<GifModel> {
        return gifApiModel?.data?.map { gifApiData ->
            GifModel(
                id = gifApiData.id,
                title = gifApiData.title,
                gifUrl = gifApiData.images.downsized_large.url
            )
        }
            ?: throw IllegalArgumentException("gifApiModel is null")


    }
}