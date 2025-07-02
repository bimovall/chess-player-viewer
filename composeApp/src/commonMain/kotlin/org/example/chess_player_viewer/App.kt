package org.example.chess_player_viewer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.chess_player_viewer.ui.feature.home.HomeScreen
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
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) { innerPadding ->
            HomeScreen(
                modifier = Modifier.padding(
                    innerPadding
                )
            )
        }
    }
}