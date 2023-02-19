package com.example.giphy.data.api_models

data class Data(
    val bitly_gif_url: String,
    val bitly_url: String,
    val embed_url: String,
    val id: String,
    val images: Images,
    val title: String,
)