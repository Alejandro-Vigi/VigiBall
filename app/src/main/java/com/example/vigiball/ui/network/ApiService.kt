package com.example.vigiball.ui.network

import com.example.vigiball.ui.model.CharacterResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface DragonBallApiService {
    @GET("characters?limit=58")
    suspend fun getCharacters(): ApiResponse
}

data class ApiResponse(
    val items: List<CharacterResponse>,
    val meta: MetaData
)

data class MetaData(
    val totalItems: Int
)

object DragonBallApi {
    private const val BASE_URL = "https://dragonball-api.com/api/"

    val service: DragonBallApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DragonBallApiService::class.java)
    }
}