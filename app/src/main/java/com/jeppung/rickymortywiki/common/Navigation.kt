package com.jeppung.rickymortywiki.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.rounded.Language
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.People
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jeppung.rickymortywiki.R
import com.jeppung.rickymortywiki.feature_character.presentation.character_detail.CharacterDetailScreen
import com.jeppung.rickymortywiki.feature_character.presentation.characters.CharacterScreen
import com.jeppung.rickymortywiki.feature_character.presentation.characters.CharacterViewModel
import com.jeppung.rickymortywiki.feature_location.presentation.locations.LocationScreen
import com.jeppung.rickymortywiki.feature_titan.presentation.episode.EpisodeScreen


@Composable
fun Navigation(navController: NavHostController, paddingValues: PaddingValues, onSelectedRoutes: (Routes) -> Unit) {
    NavHost(
        navController = navController,
        startDestination = Routes.Characters.screen_route
    ) {
        composable(route = Routes.Characters.screen_route) {
            Box(Modifier.padding(paddingValues)) {
                CharacterScreen(navController)
            }
        }
        composable(
            route = Routes.CharacterDetail.screen_route + "/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            onSelectedRoutes(Routes.CharacterDetail)
            Box(Modifier.padding(paddingValues)) {
                CharacterDetailScreen(backStackEntry.arguments?.getInt("id"))
            }
        }
        composable(route = Routes.Episodes.screen_route) {
            Box(Modifier.padding(paddingValues)) {
                EpisodeScreen()
            }
        }
        composable(route = Routes.Locations.screen_route) {
            Box(Modifier.padding(paddingValues)) {
                LocationScreen()
            }
        }
    }
}

sealed class Routes(var title: String, var icon: ImageVector, var screen_route: String) {
    object Characters : Routes("Characters", Icons.Rounded.People, "characters")
    object Episodes : Routes("Episodes", Icons.Rounded.List, "episodes")
    object Locations : Routes("Locations", Icons.Rounded.Language, "locations")
    object CharacterDetail: Routes("Character Details", Icons.Rounded.Language, "character_detail")

}


