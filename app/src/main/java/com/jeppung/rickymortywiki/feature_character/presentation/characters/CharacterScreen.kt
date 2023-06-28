package com.jeppung.rickymortywiki.feature_character.presentation.characters

import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.jeppung.rickymortywiki.common.Routes
import com.jeppung.rickymortywiki.feature_character.domain.model.Character


@Composable
fun CharacterScreen(navController: NavHostController) {
    val viewModel: CharacterViewModel = hiltViewModel()
    val data = viewModel.flow.collectAsLazyPagingItems()
    val lazyGridState = rememberLazyGridState()

    when (data.loadState.refresh) {
        is LoadState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is LoadState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error")
            }
        }

        else -> {
            CharacterList(data = data, lazyGridState, navController)
        }
    }

    when (data.loadState.append) {
        is LoadState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }
        }
        else -> {}
    }


}

@Composable
fun CharacterList(data: LazyPagingItems<Character>, lazyGridState: LazyGridState, navController: NavHostController) {
    LazyVerticalGrid(
        state = lazyGridState,
        modifier = Modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(
            data.itemCount,
        ) { index ->
            CharacterCard(data = data[index]!!) {
                navController.navigate(Routes.CharacterDetail.screen_route + "/$it")
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterCard(data: Character, onCardClick: (Int) -> Unit) {
    var statusColor by remember {
        mutableStateOf(Color.Black)
    }

    if (data.status == "Alive") {
        statusColor = Color.Green
    } else if (data.status == "Dead") {
        statusColor = Color.Red
    } else {
        statusColor = Color.Gray
    }

    Card(
        onClick = {onCardClick(data.id)}
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = data.image,
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(data.name, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(data.species, fontSize = 10.sp)
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(statusColor)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(data.status, fontSize = 10.sp, fontWeight = FontWeight.Medium)
                }
            }

        }
    }
}