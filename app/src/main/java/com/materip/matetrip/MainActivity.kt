package com.materip.matetrip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.materip.feature_home.ui.FabButton
import com.materip.matetrip.component.MateTripBottomBar
import com.materip.matetrip.navigation.GetTopBar
import com.materip.matetrip.navigation.Screen
import com.materip.matetrip.navigation.SetUpNavGraph
import com.materip.matetrip.ui.theme.MatetripTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MatetripTheme {
                val navController = rememberNavController()
                val currentBackStack by navController.currentBackStackEntryAsState()
                val currentRoute = currentBackStack?.destination?.route

                Scaffold(
                    topBar = {
                        GetTopBar(
                            currentRoute = currentRoute,
                            navController = navController
                        )
                    },
                    floatingActionButton = {
                        if (currentRoute == Screen.Home.route) {
                            FabButton(
                                onPostClick = { navController.navigate(Screen.Post.route) },
                                modifier = Modifier.padding(end = 20.dp, bottom = 35.dp)
                            )
                        }
                    },
                    bottomBar = {
                        MateTripBottomBar(
                            onHomeClick = { navController.navigate(Screen.Home.route) },
                            onMyPageClick = { navController.navigate(Screen.MyPage.route) },
                            onSettingClick = { navController.navigate(Screen.Setting.route) }
                        )
                    }
                ) { innerPadding ->
                    SetUpNavGraph(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}