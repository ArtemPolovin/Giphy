package com.example.giphy.data.network
import com.example.giphy.API_KEY
import com.example.giphy.GIPHY_API_BASE_URL
import com.example.giphy.data.api_models.GifApiModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface GifApiService {

    @GET("/v1/gifs/trending")
    suspend fun getGifs() : Response<GifApiModel>

    companion object {
        operator fun invoke(): GifApiService {
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url
                    .newBuilder()
                    .addQueryParameter("api_key", API_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)

            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor).build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(GIPHY_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GifApiService::class.java)
        }
    }
}