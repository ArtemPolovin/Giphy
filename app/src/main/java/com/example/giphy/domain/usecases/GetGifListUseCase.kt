package com.example.giphy.domain.usecases

import com.example.giphy.domain.repository.GifRepository

class GetGifListUseCase(private val gifRepository: GifRepository) {
    suspend fun execute() = gifRepository.getGifs()
}