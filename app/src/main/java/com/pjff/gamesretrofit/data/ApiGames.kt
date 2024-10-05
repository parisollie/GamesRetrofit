package com.pjff.gamesretrofit.data

import com.pjff.gamesretrofit.model.GamesModel
import com.pjff.gamesretrofit.model.SingleGameModel
import com.pjff.gamesretrofit.util.Constants.Companion.API_KEY
import com.pjff.gamesretrofit.util.Constants.Companion.ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//Vid 140, creamos nuestra interfaz
interface ApiGames {

    //Vid 142, para el uso de las constantes
    @GET(ENDPOINT + API_KEY )
    suspend fun getGames(): Response<GamesModel>

    //Vid 231
    @GET(ENDPOINT + API_KEY )
    suspend fun getGamesPaging(@Query("page") page: Int, @Query("page_size") pageSize: Int): GamesModel

    //Vid 149
    @GET("$ENDPOINT/{id}$API_KEY")
    suspend fun getGameById(@Path(value = "id")id : Int): Response<SingleGameModel>

    //Vis 226
    @GET("$ENDPOINT/{name}$API_KEY")
    suspend fun getGameByName(@Path(value = "name")name : String): Response<SingleGameModel>

}