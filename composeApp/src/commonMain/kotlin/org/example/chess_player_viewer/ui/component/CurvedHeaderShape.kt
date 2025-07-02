package org.example.chess_player_viewer.ui.component

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class CurvedHeaderShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            moveTo(0f, 0f)
            lineTo(0f, size.height * 0.8f)
            quadraticTo(
                size.width / 2, size.height,
                size.width, size.height * 0.8f
            )
            lineTo(size.width, 0f)
            close()
        }
        return Outline.Generic(path)
    }
}