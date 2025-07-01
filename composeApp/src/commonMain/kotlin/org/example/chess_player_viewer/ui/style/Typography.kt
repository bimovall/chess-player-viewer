package org.example.chess_player_viewer.ui.style

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import chessplayerviewer.composeapp.generated.resources.Montserrat_Bold
import chessplayerviewer.composeapp.generated.resources.Montserrat_Light
import chessplayerviewer.composeapp.generated.resources.Montserrat_Medium
import chessplayerviewer.composeapp.generated.resources.Montserrat_Regular
import chessplayerviewer.composeapp.generated.resources.Montserrat_SemiBold
import chessplayerviewer.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font

@Composable
fun MontserratFontFamily() = FontFamily(
    Font(Res.font.Montserrat_Light, weight = FontWeight.Light),
    Font(Res.font.Montserrat_Regular, weight = FontWeight.Normal),
    Font(Res.font.Montserrat_Medium, weight = FontWeight.Medium),
    Font(Res.font.Montserrat_SemiBold, weight = FontWeight.SemiBold),
    Font(Res.font.Montserrat_Bold, weight = FontWeight.Bold),
)

@Composable
fun MontserratTypography() = Typography().run {
    val fontFamily = MontserratFontFamily()
    copy(
        displayLarge = displayLarge.copy(fontFamily = fontFamily, fontWeight = FontWeight.Bold),
        displayMedium = displayMedium.copy(fontFamily = fontFamily),
        displaySmall = displaySmall.copy(fontFamily = fontFamily),
        headlineLarge = headlineLarge.copy(fontFamily = fontFamily),
        headlineMedium = headlineMedium.copy(fontFamily = fontFamily),
        headlineSmall = headlineSmall.copy(fontFamily = fontFamily),
        titleLarge = titleLarge.copy(fontFamily = fontFamily, fontWeight = FontWeight.Bold),
        titleMedium = titleMedium.copy(fontFamily = fontFamily, fontWeight = FontWeight.Medium, fontSize = 18.sp),
        titleSmall = titleSmall.copy(fontFamily = fontFamily),
        bodyLarge = bodyLarge.copy(fontFamily = fontFamily),
        bodyMedium = bodyMedium.copy(fontFamily = fontFamily, fontSize = 16.sp),
        bodySmall = bodySmall.copy(fontFamily = fontFamily),
        labelLarge = labelLarge.copy(fontFamily = fontFamily),
        labelMedium = labelMedium.copy(fontFamily = fontFamily),
        labelSmall = labelSmall.copy(fontFamily = fontFamily)
    )
}