package com.pjff.gamesretrofit.di

import com.pjff.gamesretrofit.data.ApiGames
import com.pjff.gamesretrofit.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

//Vid 140,
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    //Vid 140,para poder inyectar ponemos singleton y provides
    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        //Vid 140,configuramos la salida.
        return Retrofit.Builder()
            //Agregamos la BASE_URL DE Constants
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesAPiGames(retrofit: Retrofit) : ApiGames {
        return retrofit.create(ApiGames::class.java)
    }
}








