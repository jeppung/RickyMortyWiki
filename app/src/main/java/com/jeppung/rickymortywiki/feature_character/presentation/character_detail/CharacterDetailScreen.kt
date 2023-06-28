package com.jeppung.rickymortywiki.feature_character.presentation.character_detail


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jeppung.rickymortywiki.R
import com.jeppung.rickymortywiki.feature_character.domain.model.Character


@Composable
fun CharacterDetailScreen(id: Int?) {
    var uiState by remember { mutableStateOf<CharacterDetailUiState>(CharacterDetailUiState.Loading) }
    val viewModel = hiltViewModel<CharacterDetailViewModel>()

    LaunchedEffect(LocalContext.current) {
        viewModel.characterDetailUiState.observeForever {
            uiState = it
        }

        id?.let {
            viewModel.getSingleCharacter(it)
        }
    }

    when (uiState) {
        is CharacterDetailUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is CharacterDetailUiState.Success -> {
            CharacterDetail(data = (uiState as CharacterDetailUiState.Success).data)
        }

        is CharacterDetailUiState.Error -> {
            val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.error_2))
            val progress by animateLottieCompositionAsState(composition = composition, iterations = LottieConstants.IterateForever )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(),
                contentAlignment = Alignment.Center
            ) {
                LottieAnimation(composition = composition, progress = { progress }, modifier = Modifier
                    .size(250.dp)
                    .padding(0.dp))
                Text("Something went wrong :(", fontSize = 14.sp, modifier = Modifier
                    .align(
                        Alignment.BottomCenter
                    )
                    .padding(bottom = 32.dp))
            }
        }
    }
}

@Composable
fun CharacterDetail(data: Character) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(data.name)
    }
}

