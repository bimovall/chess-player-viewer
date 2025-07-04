package org.example.chess_player_viewer.ui.feature.leaderboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import chessplayerviewer.composeapp.generated.resources.Res
import chessplayerviewer.composeapp.generated.resources.ic_arrow_left
import chessplayerviewer.composeapp.generated.resources.ic_selected
import chessplayerviewer.composeapp.generated.resources.leaderboard
import org.example.chess_player_viewer.domain.model.filterLeaderboards
import org.example.chess_player_viewer.ui.component.CurvedHeader
import org.example.chess_player_viewer.ui.feature.leaderboard.component.LeaderboardItem
import org.example.chess_player_viewer.ui.feature.leaderboard.component.PodiumAvatar
import org.example.chess_player_viewer.ui.feature.leaderboard.component.PodiumIdentity
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun LeaderboardScreen(
    modifier: Modifier = Modifier,
    viewModel: LeaderboardViewModel = koinInject(),
    onBackPressed: () -> Unit
) {
    val headerHeightDp by remember { mutableStateOf(200.dp) }
    val overlapFraction = 0.1f
    val overlapOffset = with(LocalDensity.current) { (headerHeightDp * overlapFraction).toPx() }.dp
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberLazyListState()

    Column(modifier = modifier) {
        TopSection(modifier = Modifier.height(headerHeightDp), uiState,
            onBackPressed = {
                onBackPressed.invoke()
        }, onSelectedFilter = {
            viewModel.updateFilter(it)
        })

        when (val state = uiState.value) {
            is LeaderboardUiState.Error -> {
                val error = state.error
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    //TODO move to new composable
                    Text("Error: ${error.message}", color = Color.Red)
                }
            }

            is LeaderboardUiState.Loading -> {
                Box(
                    Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is LeaderboardUiState.Success -> {
                val items = state.leaderboards.data
                val error = state.error

                LaunchedEffect(error) {
                    if (error != null) {
                        //TODO show snackbar or toast
                        println("show error snackbar")
                    }
                }
                LaunchedEffect(key1 = state.selectedFilter) {
                    scrollState.requestScrollToItem(0)
                }

                LeaderboardAvatar(modifier = Modifier.offset(y = -overlapOffset), state)

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                    state = scrollState

                ) {
                    itemsIndexed(items[state.selectedFilter] ?: listOf(), key = { index, item ->
                        item.id
                    }) { index, item ->
                        LeaderboardItem(
                            index + 1,
                            item.title,
                            item.name.ifBlank { item.username },
                            item.score,
                            avatar = item.avatar,
                            onClick = {

                            })
                    }
                }
            }
        }
    }
}

@Composable
fun LeaderboardAvatar(modifier: Modifier = Modifier, state: LeaderboardUiState.Success) {
    val leaderboard = state.leaderboards.data[state.selectedFilter] ?: listOf()
    if (leaderboard.size < 3) return
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier.fillMaxWidth().padding(horizontal = 8.dp)
    ) {
        PodiumGroup(
            modifier = Modifier.offset(y = (-16).dp).weight(1f),
            number = 2,
            rating = leaderboard[1].score.toString(),
            title = leaderboard[1].title,
            name = leaderboard[1].name.ifBlank { leaderboard[1].username },
            avatar = leaderboard[1].avatar
        )

        PodiumGroup(
            modifier = Modifier.weight(1f),
            number = 1,
            rating = leaderboard[0].score.toString(),
            title = leaderboard[0].title,
            name = leaderboard[0].name.ifBlank { leaderboard[0].username },
            avatar = leaderboard[0].avatar
        )

        PodiumGroup(
            modifier = Modifier.offset(y = (-16).dp).weight(1f),
            number = 3,
            rating = leaderboard[2].score.toString(),
            title = leaderboard[2].title,
            name = leaderboard[2].name.ifBlank { leaderboard[2].username },
            avatar = leaderboard[2].avatar
        )
    }
}

@Composable
fun PodiumGroup(
    number: Int,
    avatar: String,
    rating: String,
    title: String,
    name: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        PodiumAvatar(number, avatar)
        PodiumIdentity(rating, title, name)
    }
}


@Composable
fun TopSection(
    modifier: Modifier = Modifier,
    uiState: State<LeaderboardUiState>,
    onBackPressed: () -> Unit,
    onSelectedFilter: (String) -> Unit
) {
    CurvedHeader(
        modifier = modifier
            .fillMaxWidth()

    ) {

        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                IconButton(
                    onClick = {
                        onBackPressed.invoke()
                    },
                    modifier = Modifier.height(36.dp).width(36.dp)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_arrow_left),
                        contentDescription = "",
                        tint = Color.White
                    )
                }

                Spacer(modifier = Modifier.width(24.dp))

                Text(
                    stringResource(Res.string.leaderboard),
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.White
                    )
                )
            }

            when (uiState.value) {
                is LeaderboardUiState.Error -> {
                }

                is LeaderboardUiState.Loading -> {
                }

                is LeaderboardUiState.Success -> {
                    FilterChipGroup(
                        onSelectedFilter = onSelectedFilter
                    )
                }
            }


        }
    }
}

@Composable
fun FilterChipGroup(onSelectedFilter: (String) -> Unit) {

    var selectedIdx by mutableStateOf(0)
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        itemsIndexed(filterLeaderboards.keys.toList()) { index, item ->
            FilterChip(
                index == selectedIdx,
                onClick = {
                    selectedIdx = index
                    onSelectedFilter(item)
                },
                label = {
                    Text(
                        filterLeaderboards[item].orEmpty(),
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Medium
                        )
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    selectedLabelColor = Color.White,
                    selectedContainerColor = Color.White.copy(alpha = 0.5F),
                    containerColor = Color.Transparent,
                    labelColor = Color.White
                ),
                border = FilterChipDefaults.filterChipBorder(
                    selected = selectedIdx == index,
                    enabled = true,
                    selectedBorderColor = Color.Transparent,
                    borderColor = Color.White
                ),
                leadingIcon = {
                    if (selectedIdx == index) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_selected),
                            "Icon",
                            modifier = Modifier.size(16.dp),
                            tint = Color.White
                        )
                    }
                }
            )
        }
    }
}
