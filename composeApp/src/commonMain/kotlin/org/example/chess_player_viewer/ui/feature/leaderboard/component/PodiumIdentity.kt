package org.example.chess_player_viewer.ui.feature.leaderboard.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.chess_player_viewer.ui.component.BadgeItem

@Composable
fun PodiumIdentity(rating: String, title: String, name: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            rating, style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 14.sp
            ), modifier = Modifier.padding(top = 8.dp)
        )
        Row {
            if (title.isNotBlank()) {
                BadgeItem(title, modifier = Modifier.align(Alignment.CenterVertically))
            }
            Text(
                name,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(start = 8.dp).align(Alignment.CenterVertically)
            )
        }
    }
}