package com.pjff.gamesretrofit.model

//Vid 142 ,hacemos nuestros dos modelos,se escribe el nombre tal cual.
data class GamesModel(
    val count: Int,
    //Conectamos los diferentes nodos en el json
    val results: List<GameList>
)

data class GameList(
    val id: Int,
    val name : String,
    val background_image: String
)
