package com.jeppung.rickymortywiki.feature_character.presentation.characters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.google.gson.Gson
import com.jeppung.rickymortywiki.feature_character.data.data_source.CharacterPagingSource
import com.jeppung.rickymortywiki.feature_character.domain.model.Character
import com.jeppung.rickymortywiki.feature_character.domain.use_case.CharacterUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterUseCases: CharacterUseCases
): ViewModel() {
    val flow = Pager(
        PagingConfig(pageSize = 20, initialLoadSize = 20)
    ) {
        characterUseCases.getCharacters()
    }.flow.cachedIn(viewModelScope)
}
