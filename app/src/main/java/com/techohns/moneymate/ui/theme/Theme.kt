package com.techohns.moneymate.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Emerald,
    secondary = Gold,
    background = Charcoal,
    surface = CharcoalLight,
    onPrimary = Charcoal,
    onSecondary = Charcoal,
    onBackground = OnSurface,
    onSurface = OnSurface,
    error = Error
)

private val LightColorScheme = lightColorScheme(
    primary = EmeraldDark,
    secondary = Gold,
    background = OnSurface,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Charcoal,
    onBackground = Charcoal,
    onSurface = Charcoal,
    error = Error
)

@Composable
fun MaliMateTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}