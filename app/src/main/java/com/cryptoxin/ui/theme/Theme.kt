package com.cryptoxin.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200,
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200,

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun CryptoxinTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )

    val systemUiController = rememberSystemUiController()
    if(darkTheme){
        systemUiController.setSystemBarsColor(
            color = chonolulublue

        )
    }else{
        systemUiController.setSystemBarsColor(
            color = chonolulublue
        )
    }
}

//==========================================//
val MasterCardYellow = Color(color = 0xFFF79E1B)
val WalletGreen = Color(color = 0xFF34D39A)
val WalletBrown = Color(color = 0xFFFFA58C)
val WalletCyan = Color(color = 0xFF04D3EE)
val WalletYellow = Color(color = 0xFFFEEA60)
val WalletPink = Color(color = 0xFFFFB0C0)
val WalletLightCyan = Color(color = 0xFF47EAFE)
val WalletOceanBlue3 = Color(color = 0xFF3B9CF6)
val CardBackLineLightPurple = Color(color = 0xFFC9C8FF)
val CongratulationsGreen = Color(color = 0xFF06D388)