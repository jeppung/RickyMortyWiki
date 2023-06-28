package com.jeppung.rickymortywiki

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jeppung.rickymortywiki.common.Navigation
import com.jeppung.rickymortywiki.common.Routes
import com.jeppung.rickymortywiki.common.components.BottomNavigation
import com.jeppung.rickymortywiki.common.components.TopBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(Build.VERSION.SDK_INT >= 33){
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    123
                )
            }
        }

        setContent {
            MainScreenView()
        }
    }
}

@Composable
fun MainScreenView() {
    val navController = rememberNavController()
    var bottomNavState by remember { mutableStateOf(true) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    var topAppBarTitle by remember { mutableStateOf("") }

    if(navBackStackEntry?.destination?.route == Routes.CharacterDetail.screen_route + "/{id}"){
        bottomNavState = false
    }else{
        bottomNavState = true
    }

    Log.d("TAGGG", navBackStackEntry?.destination?.route.toString())
    when(navBackStackEntry?.destination?.route) {
        Routes.Characters.screen_route -> topAppBarTitle = Routes.Characters.title
        Routes.CharacterDetail.screen_route + "/{id}" -> topAppBarTitle = Routes.CharacterDetail.title
        Routes.Locations.screen_route -> topAppBarTitle = Routes.Locations.title
        Routes.Episodes.screen_route -> topAppBarTitle = Routes.Episodes.title
    }

    Scaffold(
        topBar = { TopBar(topAppBarTitle) },
        bottomBar = {
            BottomNavigation(bottomNavState) { route ->
                navController.navigate(route.screen_route)
            }
        }
    ) { paddingValues ->
        Navigation(
            navController = navController,
            paddingValues
        ) {

        }
    }
}
