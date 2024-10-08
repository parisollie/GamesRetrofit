package com.pjff.gamesretrofit.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
//import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pjff.gamesretrofit.components.MainImage
import com.pjff.gamesretrofit.components.MainTopBar
import com.pjff.gamesretrofit.components.MetaWebsite
import com.pjff.gamesretrofit.components.ReviewCard
import com.pjff.gamesretrofit.util.Constants.Companion.CUSTOM_BLACK
import com.pjff.gamesretrofit.viewModel.GamesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//Vid 148,
//Vid 228, ponemos name:String?
fun DetailView(viewModel: GamesViewModel, navController: NavController, id: Int,name:String?) {
    //Vid 149
    LaunchedEffect(Unit) {
        //viewModel.getGameById(id)
        if (id == 0){
            //Le ponemos gion para remplazar los espacios Resident Evil
            name?.let { viewModel.getGameByName(it.replace(" ","-")) }
        }else {
            viewModel.getGameById(id)
        }
    }

    //Vid 153
    DisposableEffect(Unit){
        onDispose {
            viewModel.clean()
        }
    }

    Scaffold(
        //Vid 149
        topBar = {
            MainTopBar(title = viewModel.state.name, showBackButton = true, onClickBackButton = {
                navController.popBackStack() }) {}
        }
    ) {
        ContentDetailView(it, viewModel)
    }
}

@Composable
//Vid 149
fun ContentDetailView(pad: PaddingValues, viewModel: GamesViewModel) {
    val state = viewModel.state

    Column(
        modifier = Modifier
            .padding(pad)
            .background(Color(CUSTOM_BLACK))
    ) {
        MainImage(image = state.background_image)
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 5.dp)
        ) {
            MetaWebsite(state.website)
            ReviewCard(state.metacritic)
        }

        //Vid 153, para la descripcion del juego
        val scroll = rememberScrollState(0)
        Text(text = state.description_raw,
        color = Color.White,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(start = 15.dp, end = 15.dp, bottom = 10.dp)
                .verticalScroll(scroll)
            )

    }
}