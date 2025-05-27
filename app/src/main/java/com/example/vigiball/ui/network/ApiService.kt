
package com.example.vigiball.ui.network

import com.example.vigiball.ui.model.CharacterResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/*
*  Spanish:
*  Esta clase maneja la conexión con la API de Dragon Ball, tiene dos funciones principales, una para obtener la
*  lista de personajes y otra para los detalles de cada uno, usa Retrofit que es una buena librería para hacer llamadas
*  a APIs, también define cómo se estructura la respuesta con los datos y metadatos, la parte de DragonBallApi es donde
*  se configura la URL base y se crea el servicio.
*
*  English:
*  // This class handles the connection to the Dragon Ball API, it has two main functions, one to get the character
*  list and another for details of each, it uses Retrofit which is a cool library for API calls, it also defines how
*  the response is structured with data and metadata, the DragonBallApi part is where the base URL is configured and the
*  service is created.
* */

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