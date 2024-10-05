package com.pjff.gamesretrofit.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.pjff.gamesretrofit.components.Loader
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
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
    //val games by viewModel.games.collectAsState()
    //Vid 233
    val gamesPage = viewModel.gamesPage.collectAsLazyPagingItems()

    //Vid 127
    var search by remember { mutableStateOf("") }

    //Vid 227, agregamos una columna
    Column (modifier = Modifier.padding(pad),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            value = search,
            onValueChange = { search = it },
            label = { Text(text = "Search") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                //Vid 228
                onDone = {
                    val zero = 0
                    navController.navigate("DetailView/${zero}/?${search}")
                }
            ),
            modifier = Modifier.fillMaxWidth()
                .padding(start = 18.dp, end = 10.dp)
        )

        //Vid 144
        LazyColumn(
            modifier = Modifier
                //Vid 147,
                .background(Color(CUSTOM_BLACK))
        ){
            items(gamesPage.itemCount){ index ->
                //Vid 233
                val item = gamesPage[index]
                if(item != null){
                    //Vid 147
                    CardGame(item) {
                        //Vid 148,
                        //Vid 229 /?${search}
                        navController.navigate("DetailView/${item.id}/?${search}")
                    }
                    //Vid 147
                    Text(text = item.name,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
            }

            //Vid 235, agregamos el loader
            when(gamesPage.loadState.append){
                is LoadState.NotLoading -> Unit
                LoadState.Loading -> {
                    item {
                        Column(
                            modifier = Modifier
                                .fillParentMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Loader()
                        }
                    }
                }
                is LoadState.Error -> {
                    item {
                        Text(text = "Error al cargar")
                    }
                }
            }




        }//Lazzy
    }

}