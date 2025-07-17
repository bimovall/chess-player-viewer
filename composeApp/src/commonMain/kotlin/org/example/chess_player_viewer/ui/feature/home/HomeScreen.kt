package org.example.chess_player_viewer.ui.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import chessplayerviewer.composeapp.generated.resources.Res
import chessplayerviewer.composeapp.generated.resources.ic_next
import chessplayerviewer.composeapp.generated.resources.leaderboard
import chessplayerviewer.composeapp.generated.resources.recently_viewed
import chessplayerviewer.composeapp.generated.resources.streamer
import chessplayerviewer.composeapp.generated.resources.title
import chessplayerviewer.composeapp.generated.resources.favorite_player
import org.example.chess_player_viewer.domain.model.Profile
import org.example.chess_player_viewer.ui.component.PlayerCard
import org.example.chess_player_viewer.ui.style.Color.BrightRed
import org.example.chess_player_viewer.ui.style.Color.DeepOrange
import org.example.chess_player_viewer.ui.style.Color.DeepPurple
import org.example.chess_player_viewer.ui.style.Color.LightOrange
import org.example.chess_player_viewer.ui.style.Color.LightPurple
import org.example.chess_player_viewer.ui.style.Color.SoftRed
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel<HomeViewModel>(),
    onClickFavoritePlayer: () -> Unit,
    onClickLeaderboard: () -> Unit,
    onClickStreamer: () -> Unit,
    onClickToProfile: (username: String) -> Unit,
    modifier: Modifier = Modifier
) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getRecentlyViewed()
    }

    Column(modifier = modifier.fillMaxHeight()) {
        Text(
            text = stringResource(Res.string.title),
            style = MaterialTheme.typography.titleLarge.copy(),
            modifier = Modifier.padding(vertical = 16.dp)
        )

        MenuGrid(
            onClickFavoritePlayer = onClickFavoritePlayer,
            onClickLeaderboard = onClickLeaderboard,
            onClickStreamer = onClickStreamer
        )

        Text(
            text = stringResource(Res.string.recently_viewed),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        when (uiState.value) {
            is HomeUiState.Error -> {
                //TODO add error state
            }

            is HomeUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

            is HomeUiState.Success -> {
                RecentlyViewedContent((uiState.value as HomeUiState.Success).profiles, onClickToProfile)
            }
        }


    }
}

@Composable
fun RecentlyViewedContent(profiles: List<Profile>, onClickToProfile: (username: String) -> Unit) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(profiles) {
            PlayerCard(avatar = it.avatar, title = it.title, name = it.username, onClick = {
                onClickToProfile(it.username)
            })
        }
    }
}

@Composable
fun MenuGrid(
    modifier: Modifier = Modifier,
    onClickLeaderboard: () -> Unit,
    onClickFavoritePlayer: () -> Unit,
    onClickStreamer: () -> Unit,
) {

    Row(
        modifier.height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        MainMenuCard(
            title = stringResource(Res.string.leaderboard),
            gradientColors = listOf(LightPurple, DeepPurple),
            modifier = Modifier.fillMaxSize().weight(1f),
            onClick = onClickLeaderboard,
            spacer = {
                Spacer(Modifier.weight(1f))
            }
        )

        Column(
            Modifier.weight(1F).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            MainMenuCard(
                title = stringResource(Res.string.favorite_player),
                gradientColors = listOf(SoftRed, BrightRed),
                modifier = Modifier.fillMaxWidth(),
                onClick = onClickFavoritePlayer,
                spacer = {
                    Spacer(Modifier.height(16.dp))
                }
            )
            MainMenuCard(
                title = stringResource(Res.string.streamer),
                gradientColors = listOf(LightOrange, DeepOrange),
                Modifier.fillMaxWidth(),
                onClick = onClickStreamer,
                spacer = {
                    Spacer(Modifier.height(16.dp))
                })

        }
    }

}


@Composable
fun MainMenuCard(
    title: String,
    gradientColors: List<Color>,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    spacer: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        onClick = onClick
    ) {
        Box(
            modifier = Modifier.background(
                brush = Brush.verticalGradient(gradientColors)
            )
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    title,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 18.sp,
                        color = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                spacer()
                Image(
                    painterResource(Res.drawable.ic_next),
                    contentDescription = "",
                    modifier = Modifier.align(Alignment.End).padding(end = 8.dp, bottom = 8.dp)
                        .height(48.dp)
                )
            }
        }
    }
}
