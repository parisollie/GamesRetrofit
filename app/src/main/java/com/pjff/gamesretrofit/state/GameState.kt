package com.pjff.gamesretrofit.state

//Vid 149, le damos valores por defecto.
data class GameState(
    val name : String = "",
    val description_raw : String = "",
    val metacritic: Int = 0,
    val website : String = "",
    val background_image: String = ""
)
