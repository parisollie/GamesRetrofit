package com.pjff.gamesretrofit.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pjff.gamesretrofit.components.CardGame
import com.pjff.gamesretrofit.components.MainTopBar
import com.pjff.gamesretrofit.util.Constants.Companion.CUSTOM_BLACK
import com.pjff.gamesretrofit.viewModel.GamesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//Vid 144
fun HomeView(viewModel: GamesViewModel, navController: NavController){
    //Vid 145,
    Scaffold(
        topBar = {
            //Vid 155
            MainTopBar(title = "API GAMES", onClickBackButton = {}) {
                navController.navigate("SearchGameView")
            }
        }
    ) {
        //Vid 145,
        //Vid 148,agregamos el navController
        ContentHomeView(viewModel, it, navController)
    }
    
}

@Composable
//Vid 145,
fun ContentHomeView(viewModel: GamesViewModel, pad:PaddingValues, navController: NavController){
    val games by viewModel.games.collectAsState()
    //Vid 144
    LazyColumn(
        modifier = Modifier
            .padding(pad)
            //Vid 147,
            .background(Color(CUSTOM_BLACK))
    ){
        items(games){ item ->
            //Vid 147
            CardGame(item) {
                //Vid 148,
                navController.navigate("DetailView/${item.id}")
            }
            //Vid 147
            Text(text = item.name,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                modifier = Modifier.padding(start = 10.dp)
                )
        }
    }
}