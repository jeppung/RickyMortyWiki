package com.jeppung.rickymortywiki.feature_character.presentation.character_detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeppung.rickymortywiki.feature_character.domain.model.Character
import com.jeppung.rickymortywiki.feature_character.domain.use_case.CharacterUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val characterUseCases: CharacterUseCases
) : ViewModel() {

    private val _characterDetailUiState = MutableLiveData<CharacterDetailUiState>()
    val characterDetailUiState: LiveData<CharacterDetailUiState> = _characterDetailUiState

    fun getSingleCharacter(id: Int) {
        viewModelScope.launch {
            _characterDetailUiState.value = CharacterDetailUiState.Loading
            try {
                val response = characterUseCases.getSingleCharacter(id)
                _characterDetailUiState.value = CharacterDetailUiState.Success(response)
            }catch (e: Exception) {
                if(e is HttpException){
                    _characterDetailUiState.value = CharacterDetailUiState.Error
                }else{
                    _characterDetailUiState.value = CharacterDetailUiState.Error
                }
            }
        }
    }
}

sealed class CharacterDetailUiState() {
    object Loading: CharacterDetailUiState()
    data class Success(val data: Character): CharacterDetailUiState()
    object Error: CharacterDetailUiState()
}