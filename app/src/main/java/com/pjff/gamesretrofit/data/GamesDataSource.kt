package com.pjff.gamesretrofit.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pjff.gamesretrofit.model.GameList
import com.pjff.gamesretrofit.repository.GamesRepository

//Vid 231
//Inyectamos el repositorio
class GamesDataSource(private val repo: GamesRepository): PagingSource<Int, GameList>() {
    override fun getRefreshKey(state: PagingState<Int, GameList>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            //anchor ,es la posicion
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GameList> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = repo.getGamesPaging(nextPageNumber, 3)
            LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = if (response.results.isNotEmpty()) nextPageNumber + 1 else null
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }

}