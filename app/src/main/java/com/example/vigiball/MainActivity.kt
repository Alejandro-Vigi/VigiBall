package com.example.vigiball

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.vigiball.ui.components.ThemePreference
import com.example.vigiball.ui.theme.VigiBallTheme
import com.example.vigiball.ui.components.TopBar
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val themePreference = ThemePreference(this)

        setContent {
            val scope = rememberCoroutineScope()
            val isDarkTheme by themePreference.isDarkMode.collectAsState(initial = false)

            val view = LocalView.current
            if (!view.isInEditMode) {
                SideEffect {
                    val window = (view.context as Activity).window
                    val insetsController = WindowCompat.getInsetsController(window, view)
                    insetsController.isAppearanceLightStatusBars = !isDarkTheme
                }
            }

            VigiBallTheme(darkTheme = isDarkTheme) {
                TopBar(
                    isDarkTheme = isDarkTheme,
                    onToggleTheme = { newValue ->
                        scope.launch {
                            themePreference.saveDarkMode(newValue)
                        }
                    }
                )
            }
        }
    }
}
