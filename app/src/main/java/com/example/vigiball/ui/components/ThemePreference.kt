package com.example.vigiball.ui.components

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/*
 Spanish:
 Esta clase maneja las preferencias del tema oscuro/claro de la app, usa DataStore
 para guardar y leer si el modo oscuro está activado, la variable isDarkMode es un
 Flow que siempre está pendiente del valor actual, y saveDarkMode permite cambiar el
 valor cuando el usuario cambia la configuración, guarda bajo la clave "dark_mode"
 en un archivo llamado "settings".

 English:
 This class handles the app's dark/light theme preferences, it uses DataStore to
 save and read if dark mode is enabled, the isDarkMode variable is a Flow that always
 watches the current value, and saveDarkMode lets you change the value when user updates
 settings, everything gets saved under the key "dark_mode" in a file called "settings".
 */

private val Context.dataStore by preferencesDataStore("settings")

class ThemePreference(private val context: Context) {

    companion object {
        private val DARK_MODE_KEY = booleanPreferencesKey("dark_mode")
    }

    val isDarkMode: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[DARK_MODE_KEY] == true
    }

    suspend fun saveDarkMode(isDarkMode: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DARK_MODE_KEY] = isDarkMode
        }
    }
}
