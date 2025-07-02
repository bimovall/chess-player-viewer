package org.example.chess_player_viewer.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import org.example.chess_player_viewer.ui.style.Color.DeepPurple
import org.example.chess_player_viewer.ui.style.Color.LightPurple

@Composable
fun CurvedHeader(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Box(
        modifier = modifier
            .clip(CurvedHeaderShape())
            .background(brush = Brush.verticalGradient(listOf(LightPurple, DeepPurple)))

    ) {
        content()
    }

}