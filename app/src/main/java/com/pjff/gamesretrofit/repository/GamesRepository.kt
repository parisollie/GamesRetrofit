package com.pjff.gamesretrofit.repository

import com.pjff.gamesretrofit.data.ApiGames
import com.pjff.gamesretrofit.model.GameList
import com.pjff.gamesretrofit.model.GamesModel
import com.pjff.gamesretrofit.model.SingleGameModel
import kotlinx.coroutines.delay
import javax.inject.Inject

class GamesRepository @Inject constructor(private val apiGames: ApiGames) {

    //Vid 143 , hace una list de <GameList>
    suspend fun getGames(): List<GameList>? {
        val response = apiGames.getGames()
        //si funciona
        if (response.isSuccessful){
            return response.body()?.results
        }
        return null
    }

    //Vid 229
    suspend fun getGamesPaging(page: Int, pageSize: Int): GamesModel {
        //Vid 233
        delay(3000L)
        return apiGames.getGamesPaging(page, pageSize)
    }

    //vid 149
    suspend fun getGameById(id: Int): SingleGameModel? {
        val response = apiGames.getGameById(id)
        if(response.isSuccessful){
            return response.body()
        }
        return null
    }

    //Vid 226

    suspend fun getGameByName(name: String): SingleGameModel? {
        val response = apiGames.getGameByName(name)
        if(response.isSuccessful){
            return response.body()
        }
        return null
    }

}