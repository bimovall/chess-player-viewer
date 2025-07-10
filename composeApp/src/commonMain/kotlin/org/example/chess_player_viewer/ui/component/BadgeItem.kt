package org.example.chess_player_viewer.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.chess_player_viewer.ui.style.Color.BadgeColor

@Composable
fun BadgeItem(title: String, modifier: Modifier = Modifier) {
    if (title.isBlank()) return
    Box(
        modifier = modifier.background(
            color = BadgeColor,
            shape = RoundedCornerShape(4.dp)
        )
    ) {
        Text(
            title, style = MaterialTheme.typography.labelSmall.copy(
                color = Color.White
            ),
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
        )
    }
}