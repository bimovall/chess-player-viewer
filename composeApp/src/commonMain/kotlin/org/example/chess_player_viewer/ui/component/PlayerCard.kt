package org.example.chess_player_viewer.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.chess_player_viewer.ui.style.Color.BorderItemColor

@Composable
fun PlayerCard(avatar: String, title: String, name: String, modifier: Modifier = Modifier) {
    Card(
        border = BorderStroke(1.dp, BorderItemColor),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
            Avatar(modifier = Modifier.padding(end = 16.dp).size(36.dp), path = avatar)
            BadgeItem(title, modifier = Modifier.align(Alignment.CenterVertically))
            Text(
                name,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 8.dp).align(Alignment.CenterVertically)
            )


        }
    }
}