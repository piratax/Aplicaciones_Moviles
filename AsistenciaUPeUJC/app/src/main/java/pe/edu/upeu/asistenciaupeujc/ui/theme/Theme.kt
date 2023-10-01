package pe.edu.upeu.asistenciaupeujc.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

enum class ThemeType{RED, PURPLE, GREEN}


private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    onSecondary = md_theme_light_onSecondary,
    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

public val LightPurpleColors = lightColorScheme(
    primary = purple_theme_light_primary,
    onPrimary = purple_theme_light_onPrimary,
    primaryContainer = purple_theme_light_primaryContainer,
    onPrimaryContainer = purple_theme_light_onPrimaryContainer,
    secondary = purple_theme_light_secondary,
    onSecondary = purple_theme_light_onSecondary,
    secondaryContainer = purple_theme_light_secondaryContainer,
    onSecondaryContainer = purple_theme_light_onSecondaryContainer,
    tertiary = purple_theme_light_tertiary,
    onTertiary = purple_theme_light_onTertiary,
    tertiaryContainer = purple_theme_light_tertiaryContainer,
    onTertiaryContainer = purple_theme_light_onTertiaryContainer,
    error = purple_theme_light_error,
    onError = purple_theme_light_onError,
    errorContainer = purple_theme_light_errorContainer,
    onErrorContainer = purple_theme_light_onErrorContainer,
    outline = purple_theme_light_outline,
    background = purple_theme_light_background,
    onBackground = purple_theme_light_onBackground,
    surface = purple_theme_light_surface,
    onSurface = purple_theme_light_onSurface,
    surfaceVariant = purple_theme_light_surfaceVariant,
    onSurfaceVariant = purple_theme_light_onSurfaceVariant,
    inverseSurface = purple_theme_light_inverseSurface,
    inverseOnSurface = purple_theme_light_inverseOnSurface,
    inversePrimary = purple_theme_light_inversePrimary,
    surfaceTint = purple_theme_light_surfaceTint,
    outlineVariant = purple_theme_light_outlineVariant,
    scrim = purple_theme_light_scrim,
)


public val DarkPurpleColors = darkColorScheme(
    primary = purple_theme_dark_primary,
    onPrimary = purple_theme_dark_onPrimary,
    primaryContainer = purple_theme_dark_primaryContainer,
    onPrimaryContainer = purple_theme_dark_onPrimaryContainer,
    secondary = purple_theme_dark_secondary,
    onSecondary = purple_theme_dark_onSecondary,
    secondaryContainer = purple_theme_dark_secondaryContainer,
    onSecondaryContainer = purple_theme_dark_onSecondaryContainer,
    tertiary = purple_theme_dark_tertiary,
    onTertiary = purple_theme_dark_onTertiary,
    tertiaryContainer = purple_theme_dark_tertiaryContainer,
    onTertiaryContainer = purple_theme_dark_onTertiaryContainer,
    error = purple_theme_dark_error,
    onError = purple_theme_dark_onError,
    errorContainer = purple_theme_dark_errorContainer,
    onErrorContainer = purple_theme_dark_onErrorContainer,
    outline = purple_theme_dark_outline,
    background = purple_theme_dark_background,
    onBackground = purple_theme_dark_onBackground,
    surface = purple_theme_dark_surface,
    onSurface = purple_theme_dark_onSurface,
    surfaceVariant = purple_theme_dark_surfaceVariant,
    onSurfaceVariant = purple_theme_dark_onSurfaceVariant,
    inverseSurface = purple_theme_dark_inverseSurface,
    inverseOnSurface = purple_theme_dark_inverseOnSurface,
    inversePrimary = purple_theme_dark_inversePrimary,
    surfaceTint = purple_theme_dark_surfaceTint,
    outlineVariant = purple_theme_dark_outlineVariant,
    scrim = purple_theme_dark_scrim,
)

public val LightRedColors = lightColorScheme(
    primary = red_theme_light_primary,
    onPrimary = red_theme_light_onPrimary,
    primaryContainer = red_theme_light_primaryContainer,
    onPrimaryContainer = red_theme_light_onPrimaryContainer,
    secondary = red_theme_light_secondary,
    onSecondary = red_theme_light_onSecondary,
    secondaryContainer = red_theme_light_secondaryContainer,
    onSecondaryContainer = red_theme_light_onSecondaryContainer,
    tertiary = red_theme_light_tertiary,
    onTertiary = red_theme_light_onTertiary,
    tertiaryContainer = red_theme_light_tertiaryContainer,
    onTertiaryContainer = red_theme_light_onTertiaryContainer,
    error = red_theme_light_error,
    errorContainer = red_theme_light_errorContainer,
    onError = red_theme_light_onError,
    onErrorContainer = red_theme_light_onErrorContainer,
    background = red_theme_light_background,
    onBackground = red_theme_light_onBackground,
    surface = red_theme_light_surface,
    onSurface = red_theme_light_onSurface,
    surfaceVariant = red_theme_light_surfaceVariant,
    onSurfaceVariant = red_theme_light_onSurfaceVariant,
    outline = red_theme_light_outline,
    inverseOnSurface = red_theme_light_inverseOnSurface,
    inverseSurface = red_theme_light_inverseSurface,
    inversePrimary = red_theme_light_inversePrimary,
    surfaceTint = red_theme_light_surfaceTint,
    outlineVariant = red_theme_light_outlineVariant,
    scrim = red_theme_light_scrim,
)


public val DarkRedColors = darkColorScheme(
    primary = red_theme_dark_primary,
    onPrimary = red_theme_dark_onPrimary,
    primaryContainer = red_theme_dark_primaryContainer,
    onPrimaryContainer = red_theme_dark_onPrimaryContainer,
    secondary = red_theme_dark_secondary,
    onSecondary = red_theme_dark_onSecondary,
    secondaryContainer = red_theme_dark_secondaryContainer,
    onSecondaryContainer = red_theme_dark_onSecondaryContainer,
    tertiary = red_theme_dark_tertiary,
    onTertiary = red_theme_dark_onTertiary,
    tertiaryContainer = red_theme_dark_tertiaryContainer,
    onTertiaryContainer = red_theme_dark_onTertiaryContainer,
    error = red_theme_dark_error,
    errorContainer = red_theme_dark_errorContainer,
    onError = red_theme_dark_onError,
    onErrorContainer = red_theme_dark_onErrorContainer,
    background = red_theme_dark_background,
    onBackground = red_theme_dark_onBackground,
    surface = red_theme_dark_surface,
    onSurface = red_theme_dark_onSurface,
    surfaceVariant = red_theme_dark_surfaceVariant,
    onSurfaceVariant = red_theme_dark_onSurfaceVariant,
    outline = red_theme_dark_outline,
    inverseOnSurface = red_theme_dark_inverseOnSurface,
    inverseSurface = red_theme_dark_inverseSurface,
    inversePrimary = red_theme_dark_inversePrimary,
    surfaceTint = red_theme_dark_surfaceTint,
    outlineVariant = red_theme_dark_outlineVariant,
    scrim = red_theme_dark_scrim,
)

public val LightGreenColors = lightColorScheme(
    primary = green_theme_light_primary,
    onPrimary = green_theme_light_onPrimary,
    primaryContainer = green_theme_light_primaryContainer,
    onPrimaryContainer = green_theme_light_onPrimaryContainer,
    secondary = green_theme_light_secondary,
    onSecondary = green_theme_light_onSecondary,
    secondaryContainer = green_theme_light_secondaryContainer,
    onSecondaryContainer = green_theme_light_onSecondaryContainer,
    tertiary = green_theme_light_tertiary,
    onTertiary = green_theme_light_onTertiary,
    tertiaryContainer = green_theme_light_tertiaryContainer,
    onTertiaryContainer = green_theme_light_onTertiaryContainer,
    error = green_theme_light_error,
    errorContainer = green_theme_light_errorContainer,
    onError = green_theme_light_onError,
    onErrorContainer = green_theme_light_onErrorContainer,
    background = green_theme_light_background,
    onBackground = green_theme_light_onBackground,
    surface = green_theme_light_surface,
    onSurface = green_theme_light_onSurface,
    surfaceVariant = green_theme_light_surfaceVariant,
    onSurfaceVariant = green_theme_light_onSurfaceVariant,
    outline = green_theme_light_outline,
    inverseOnSurface = green_theme_light_inverseOnSurface,
    inverseSurface = green_theme_light_inverseSurface,
    inversePrimary = green_theme_light_inversePrimary,
    surfaceTint = green_theme_light_surfaceTint,
    outlineVariant = green_theme_light_outlineVariant,
    scrim = green_theme_light_scrim,
)


public val DarkGreenColors = darkColorScheme(
    primary = green_theme_dark_primary,
    onPrimary = green_theme_dark_onPrimary,
    primaryContainer = green_theme_dark_primaryContainer,
    onPrimaryContainer = green_theme_dark_onPrimaryContainer,
    secondary = green_theme_dark_secondary,
    onSecondary = green_theme_dark_onSecondary,
    secondaryContainer = green_theme_dark_secondaryContainer,
    onSecondaryContainer = green_theme_dark_onSecondaryContainer,
    tertiary = green_theme_dark_tertiary,
    onTertiary = green_theme_dark_onTertiary,
    tertiaryContainer = green_theme_dark_tertiaryContainer,
    onTertiaryContainer = green_theme_dark_onTertiaryContainer,
    error = green_theme_dark_error,
    errorContainer = green_theme_dark_errorContainer,
    onError = green_theme_dark_onError,
    onErrorContainer = green_theme_dark_onErrorContainer,
    background = green_theme_dark_background,
    onBackground = green_theme_dark_onBackground,
    surface = green_theme_dark_surface,
    onSurface = green_theme_dark_onSurface,
    surfaceVariant = green_theme_dark_surfaceVariant,
    onSurfaceVariant = green_theme_dark_onSurfaceVariant,
    outline = green_theme_dark_outline,
    inverseOnSurface = green_theme_dark_inverseOnSurface,
    inverseSurface = green_theme_dark_inverseSurface,
    inversePrimary = green_theme_dark_inversePrimary,
    surfaceTint = green_theme_dark_surfaceTint,
    outlineVariant = green_theme_dark_outlineVariant,
    scrim = green_theme_dark_scrim,
)

@Composable
fun AsistenciaUPeUJCTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    colorScheme: ColorScheme,
    content: @Composable () -> Unit
) {
    /*val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }*/
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes= Shapes,
        content = content
    )
}