package org.example.chess_player_viewer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.example.chess_player_viewer.ui.component.DefaultAppBar
import org.example.chess_player_viewer.ui.feature.favorite_player.FavoritePlayerScreen
import org.example.chess_player_viewer.ui.feature.home.HomeScreen
import org.example.chess_player_viewer.ui.feature.leaderboard.LeaderboardScreen
import org.example.chess_player_viewer.ui.feature.profile.ProfileScreen
import org.example.chess_player_viewer.ui.feature.profile.component.ProfileTopAppBar
import org.example.chess_player_viewer.ui.feature.streamer.StreamerScreen
import org.example.chess_player_viewer.ui.navigation.FavoritePlayerRoute
import org.example.chess_player_viewer.ui.navigation.HomeRoute
import org.example.chess_player_viewer.ui.navigation.LeaderboardRoute
import org.example.chess_player_viewer.ui.navigation.ProfileRoute
import org.example.chess_player_viewer.ui.navigation.StreamerRoute
import org.example.chess_player_viewer.ui.navigation.matchesRoute
import org.example.chess_player_viewer.ui.style.MontserratTypography
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme(
        typography = MontserratTypography()
    ) {
        val navHostController = rememberNavController()
        val currentDestination by navHostController.currentBackStackEntryAsState()
        val currentRoute = currentDestination?.destination?.route
        var collapsedProfileFraction by remember {
            mutableStateOf(0F)
        }
        var name by remember {
            mutableStateOf("")
        }
        var avatar by remember {
            mutableStateOf("")
        }

        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                when {
                    currentRoute.matchesRoute(ProfileRoute::class) -> {
                        ProfileTopAppBar(
                            collapsedFraction = collapsedProfileFraction,
                            name = name,
                            avatar = avatar,
                            onBackPressed = {
                                navHostController.popBackStack()
                            }
                        )
                    }

                    currentRoute.matchesRoute(StreamerRoute::class) -> {
                        DefaultAppBar(
                            title = "Streamer",
                            onBackPressed = {
                                navHostController.popBackStack()
                            }
                        )
                    }

                    currentRoute.matchesRoute(FavoritePlayerRoute::class) -> {
                        DefaultAppBar(
                            title = "Favorite Player",
                            onBackPressed = {
                                navHostController.popBackStack()
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->

            NavHost(
                navController = navHostController,
                startDestination = HomeRoute,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable<HomeRoute> {
                    HomeScreen(
                        onClickLeaderboard = {
                            navHostController.navigate(LeaderboardRoute)
                        },
                        onClickFavoritePlayer = {
                            navHostController.navigate(FavoritePlayerRoute)
                        },
                        onClickStreamer = {
                            navHostController.navigate(StreamerRoute)
                        },
                        onClickToProfile = {
                            navHostController.navigate(ProfileRoute(it))
                        },
                        modifier = Modifier.padding(8.dp)
                    )
                }
                composable<LeaderboardRoute> {
                    LeaderboardScreen(
                        onBackPressed = {
                            navHostController.popBackStack()
                        },
                        onProfileClicked = { username ->
                            navHostController.navigate(ProfileRoute(username))
                        }
                    )
                }
                composable<ProfileRoute> { backStackEntry ->
                    val route = backStackEntry.toRoute<ProfileRoute>()
                    ProfileScreen(
                        username = route.username,
                        onCollapsedFractionChanged = {
                            collapsedProfileFraction = it
                        },
                        onAvatarAdded = {
                            avatar = it
                        },
                        onNameAdded = {
                            name = it
                        }
                    )
                }

                composable<StreamerRoute> {
                    StreamerScreen(
                        onOpenProfile = { username ->
                            navHostController.navigate(ProfileRoute(username))
                        },
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }

                composable<FavoritePlayerRoute> {
                    FavoritePlayerScreen(
                        onRedirectToProfile = {
                            navHostController.navigate(ProfileRoute(it))
                        },
                        modifier = Modifier.padding(8.dp)
                    )
                }

            }
        }
    }
}