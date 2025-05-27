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

/*
 Spanish:
 Esta es la actividad principal de la app, aquí se configura el tema claro/oscuro
 y la edge-to-edge experience, primero se habilita el modo edge-to-edge para que la
 app use toda la pantalla, luego se usa ThemePreference para guardar y cargar la
 preferencia del tema, el SideEffect actualiza la barra de status cuando cambia el tema,
 y finalmente se aplica el tema personalizado VigiBallTheme con la TopBar que tiene el
 botón para cambiar entre modos.

 English:
 This is the app's main activity, here we set up the light/dark theme and edge-to-edge
 experience, first we enable edge-to-edge mode so the app uses the full screen, then
 ThemePreference saves and loads the theme preference, the SideEffect updates the status
 bar when theme changes, and finally we apply the custom VigiBallTheme with the TopBar
 that has the theme toggle button.
*/

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
