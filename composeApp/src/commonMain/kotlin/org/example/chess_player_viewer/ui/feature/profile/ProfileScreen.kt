package org.example.chess_player_viewer.ui.feature.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import chessplayerviewer.composeapp.generated.resources.Res
import chessplayerviewer.composeapp.generated.resources.ic_location
import chessplayerviewer.composeapp.generated.resources.ic_selected
import org.example.chess_player_viewer.ui.component.Avatar
import org.example.chess_player_viewer.ui.component.BadgeItem
import org.jetbrains.compose.resources.painterResource

@Composable
fun ProfileScreen(
    onCollapsedFractionChanged: (Float) -> Unit,
    onNameAdded: (String) -> Unit,
    onAvatarAdded: (String) -> Unit,
) {
    val listState = rememberLazyListState()

    var headerHeightPx by remember { mutableStateOf(0f) }
    val collapseFraction by remember {
        derivedStateOf {
            if (headerHeightPx == 0f) 0f
            else {
                val first = listState.firstVisibleItemIndex
                val offset = listState.firstVisibleItemScrollOffset.toFloat()
                if (first > 0) 1f
                else (offset / headerHeightPx).coerceIn(0f, 1f)
            }
        }
    }

    LaunchedEffect(collapseFraction) {
        onCollapsedFractionChanged(collapseFraction)
        //TODO temporary set to this function, move it after integrated with API
        onNameAdded("Vall")
        onAvatarAdded("")
    }

    LazyColumn(
        state = listState,
        modifier = Modifier
            .padding(horizontal = 8.dp)
    ) {
        item {
            Header(
                collapseFraction = 1f - collapseFraction,
                modifier = Modifier.onGloballyPositioned {
                    headerHeightPx = it.size.height.toFloat()
                })
        }

        item {
            Information()
        }

        item {
            StreamingPlatform(modifier = Modifier.padding(top = 16.dp))
        }

        item {
            Statistic(modifier = Modifier.padding(top = 16.dp))
        }
    }
}

@Composable
fun Statistic(modifier: Modifier = Modifier) {
    Column(modifier = modifier.wrapContentHeight()) {
        Text("Statistic", style = MaterialTheme.typography.bodyMedium)
        FlowRow(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            repeat(15) {
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .clip(RoundedCornerShape(8.dp))
                        .widthIn(100.dp, 150.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(),
                        ) {


                        }
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            "Bughouse",
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(
                                top = 16.dp,
                                start = 16.dp,
                                end = 16.dp,
                                bottom = 8.dp
                            )
                        )
                        Text(
                            "1000",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(
                                top = 8.dp,
                                start = 16.dp,
                                end = 16.dp,
                                bottom = 16.dp
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StreamingPlatform(modifier: Modifier = Modifier) {
    Column(modifier = modifier.wrapContentHeight()) {
        Text("Streaming Platform", style = MaterialTheme.typography.bodyMedium)
        FlowRow(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            repeat(4) {
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .clip(RoundedCornerShape(8.dp))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(),
                        ) {

                        }
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_selected),
                        "",
                        modifier = Modifier.padding(36.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun Information(modifier: Modifier = Modifier) {
    Column(modifier = modifier.wrapContentHeight()) {
        Text("Information", style = MaterialTheme.typography.bodyMedium)

        Card(modifier = Modifier.wrapContentHeight()) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InformationItem(
                    title = "Top Left",
                    value = "Value 1",
                    modifier = Modifier.background(Color.Yellow).fillMaxWidth().weight(1F)
                )
                InformationItem(
                    title = "Top Right",
                    value = "Value 2",
                    modifier = Modifier.background(Color.Green).fillMaxWidth().weight(1F)
                )
            }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InformationItem(
                    title = "Bottom Left",
                    value = "Value 3",
                    modifier = Modifier.background(Color.Green).fillMaxWidth().weight(1F)
                )
                InformationItem(
                    title = "Bottom Right",
                    value = "Value 4",
                    modifier = Modifier.background(Color.Yellow).fillMaxWidth().weight(1F)
                )
            }
        }
    }
}

@Composable
fun InformationItem(title: String, value: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            title,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp,
                bottom = 8.dp
            )
        )
        Text(
            value,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(
                top = 8.dp,
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp
            )
        )
    }
}

@Composable
fun Header(
    collapseFraction: Float,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(vertical = 16.dp)
            .graphicsLayer {
                alpha = collapseFraction
                scaleX = 0.8f + 0.2f * collapseFraction
                scaleY = 0.8f + 0.2f * collapseFraction
            },
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Avatar(
            path = "",
            modifier = Modifier.size(72.dp)
        )
        PersonalInformation(
            title = "GM",
            name = "Vall",
            "vall1124",
            location = "Sydney"
        )


    }
}

@Composable
fun PersonalInformation(
    title: String,
    name: String,
    username: String,
    location: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.wrapContentHeight()) {
        Row {
            BadgeItem(title, modifier = Modifier.align(Alignment.CenterVertically))
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                username,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 16.sp
                ),
                modifier = Modifier.align(Alignment.CenterVertically)
                    .weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            name,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.Black.copy(alpha = 0.5F)
            ),
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(Res.drawable.ic_location), "")
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                location,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 14.sp
                ),
            )
        }

    }

}
