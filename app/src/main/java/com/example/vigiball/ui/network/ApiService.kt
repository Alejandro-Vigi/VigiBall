
package com.example.vigiball.ui.network

import com.example.vigiball.ui.model.CharacterResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface DragonBallApiService {
    @GET("characters?limit=58")
    suspend fun getCharacters(): ApiResponse<CharacterResponse>

    @GET("characters/{id}")
    suspend fun getCharacterDetails(@Path("id") id: Int): CharacterResponse
}

data class ApiResponse<T>(
    val items: List<T>,
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