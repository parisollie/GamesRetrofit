package com.pjff.gamesretrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pjff.gamesretrofit.navigation.NavManager
import com.pjff.gamesretrofit.ui.theme.GamesRetrofitTheme
import com.pjff.gamesretrofit.viewModel.GamesViewModel
import dagger.hilt.android.AndroidEntryPoint

//Vid 140, poonemos la etiqueta para inyeccion de dependencias.
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Vid 144
        val viewModel : GamesViewModel by viewModels()
        setContent {
            GamesRetrofitTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Vid 148,
                    NavManager(viewModel)
                }
            }
        }
    }
}
