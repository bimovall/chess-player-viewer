package org.example.chess_player_viewer.ui.feature.leaderboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import chessplayerviewer.composeapp.generated.resources.Res
import chessplayerviewer.composeapp.generated.resources.ic_next
import chessplayerviewer.composeapp.generated.resources.leaderboard
import org.example.chess_player_viewer.ui.component.CurvedHeader
import org.example.chess_player_viewer.ui.feature.leaderboard.component.LeaderboardItem
import org.example.chess_player_viewer.ui.feature.leaderboard.component.PodiumAvatar
import org.example.chess_player_viewer.ui.feature.leaderboard.component.PodiumIdentity
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun LeaderboardScreen(modifier: Modifier = Modifier) {
    val headerHeightDp by remember { mutableStateOf(200.dp) }
    val overlapFraction = 0.1f
    val overlapOffset = with(LocalDensity.current) { (headerHeightDp * overlapFraction).toPx() }.dp

    Column(modifier = modifier) {
        TopSection(modifier = Modifier.height(headerHeightDp))
        LeaderboardAvatar(modifier = Modifier.offset(y = -overlapOffset))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(start = 8.dp, end = 8.dp)

        ) {
            items(count = 12) {
                LeaderboardItem(1, "WGM", "Hikaru", 2021)
            }
        }
    }
}

@Composable
fun LeaderboardAvatar(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier.fillMaxWidth().padding(horizontal = 8.dp)
    ) {
        PodiumGroup(modifier = Modifier.offset(y = (-16).dp).weight(1f))
        PodiumGroup(modifier = Modifier.weight(1f))
        PodiumGroup(modifier = Modifier.offset(y = (-16).dp).weight(1f))
    }
}

@Composable
fun PodiumGroup(modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        PodiumAvatar()
        PodiumIdentity()
    }
}


@Composable
fun TopSection(modifier: Modifier = Modifier) {
    CurvedHeader(
        modifier = modifier
            .fillMaxWidth()

    ) {

        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = {

                    },
                    modifier = Modifier.height(48.dp).width(48.dp)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_next),
                        contentDescription = ""
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

            FilterChipGroup()
        }
    }
}

@Composable
fun FilterChipGroup() {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(6) {
            FilterChip(
                it == 0,
                onClick = {

                },
                label = {
                    Text(
                        "Daily", style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Medium
                        )
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    selectedLabelColor = Color.White,
                    selectedContainerColor = Color.White.copy(alpha = 0.5F),
                    containerColor = Color.Transparent
                ),
                leadingIcon = {
                    if (it == 0) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_next),
                            "Icon",
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            )
        }
    }
}
