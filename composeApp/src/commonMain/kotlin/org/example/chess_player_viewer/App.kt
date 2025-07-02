package org.example.chess_player_viewer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.example.chess_player_viewer.ui.feature.home.HomeScreen
import org.example.chess_player_viewer.ui.feature.leaderboard.LeaderboardScreen
import org.example.chess_player_viewer.ui.navigation.Home
import org.example.chess_player_viewer.ui.navigation.Leaderboard
import org.example.chess_player_viewer.ui.style.MontserratTypography
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme(
        typography = MontserratTypography()
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
        ) { innerPadding ->
            val navHostController = rememberNavController()
            NavHost(
                navController = navHostController,
                startDestination = Home,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable<Home> {
                    HomeScreen(
                        onClickLeaderboard = {
                            navHostController.navigate(Leaderboard)
                        },
                        onClickTitledPlayer = {

                        },
                        onClickStreamer = {

                        }
                    )
                }
                composable<Leaderboard> {
                    LeaderboardScreen()
                }
            }
        }
    }
}