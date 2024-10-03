package com.pjff.gamesretrofit.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pjff.gamesretrofit.model.GameList
import com.pjff.gamesretrofit.repository.GamesRepository
import com.pjff.gamesretrofit.state.GameState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

//Vid 143
@HiltViewModel
class GamesViewModel @Inject constructor(private val repo: GamesRepository) : ViewModel() {

    //Traemos la lista y retorname List<GameList>
    private val _games = MutableStateFlow<List<GameList>>(emptyList())
    val games = _games.asStateFlow()

    //Vid 149
    var state by mutableStateOf(GameState())
        private set

    init {
        //Para que se ejecute
        fetchGames()
    }

    private fun fetchGames(){
        // Vid 143, hacemos la consulta.
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val result = repo.getGames()
                _games.value = result ?: emptyList()
            }
        }
    }

    //Vid 149
    fun getGameById(id : Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val result = repo.getGameById(id)
                state = state.copy(
                    name = result?.name ?: "",
                    description_raw = result?.description_raw ?: "",
                    //111 es de metacritic
                    metacritic = result?.metacritic ?: 111,
                    website = result?.website ?: "sin web",
                    background_image = result?.background_image ?: "",
                )
            }
        }
    }

    //Vid 153,limpiar las variables.
    fun clean(){
        state = state.copy(
            name =  "",
            description_raw =  "",
            metacritic =  111,
            website =  "",
            background_image = "",
        )
    }

}