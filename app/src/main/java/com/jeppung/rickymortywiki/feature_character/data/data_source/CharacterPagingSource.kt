package com.jeppung.rickymortywiki.feature_character.data.data_source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jeppung.rickymortywiki.feature_character.domain.model.Character
import com.jeppung.rickymortywiki.network.ApiService

class CharacterPagingSource(
    private val apiService: ApiService
): PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try{
            val currentPage = params.key ?: 1
            val response = apiService.getCharacters(currentPage)
            Log.d("TAGGG", response.info.toString())
            LoadResult.Page(
                data = response.results,
                prevKey = if(currentPage == 1) null else currentPage.minus(1),
                nextKey = if(currentPage == response.info.pages) null else currentPage.plus(1)
            )
        }catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}