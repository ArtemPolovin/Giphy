package com.example.giphy.data.di

import com.example.giphy.data.mappers.GifMapper
import com.example.giphy.data.network.GifApiService
import com.example.giphy.data.repository_impl.GifRepositoryImpl
import com.example.giphy.domain.repository.GifRepository
import com.example.giphy.domain.usecases.GetGifListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGifApiService(): GifApiService = GifApiService()

    @Provides
    @Singleton
    fun provideGifRepository(
        gifApiService: GifApiService,
        gifMapper: GifMapper
    ): GifRepository = GifRepositoryImpl(gifApiService,gifMapper)

    @Provides
    fun provideGifMapper() = GifMapper()

    @Provides
    fun provideGetGifListUseCase(gifRepository: GifRepository) = GetGifListUseCase(gifRepository)

}