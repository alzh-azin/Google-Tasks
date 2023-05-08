package com.example.tasks.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme

private val tasksDarkColorScheme = darkColorScheme(
    primary = Purple200,
    secondary = Purple700,
    tertiary = Teal200
)

private val tasksLightColorScheme = lightColorScheme(
    primary = Purple500,
    secondary = Purple700,
    tertiary = Teal200

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
fun TasksTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val appColorScheme = if (darkTheme) {
        tasksDarkColorScheme
    } else {
        tasksLightColorScheme
    }

    MaterialTheme(
        colorScheme = appColorScheme,
        typography = tasksTypography,
        shapes = tasksShapes,
        content = content
    )
}