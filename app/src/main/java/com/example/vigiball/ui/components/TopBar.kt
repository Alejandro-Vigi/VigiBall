package com.example.vigiball.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.vigiball.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    isDarkTheme: Boolean,
    onToggleTheme: (Boolean) -> Unit
) {
    val themeTextColor = if (isDarkTheme) Color.White else Color.Black
    val topBarColor = if (isDarkTheme) Color(0xFF181c25) else Color(0xFFFFFFFF)
    val backgroundColor = if(isDarkTheme) Color(0xFF181c25) else Color(0xFFFFFFFF)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = backgroundColor,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = topBarColor
                ),
                title = {
                    Text(
                        text = "VigiBall",
                        color = themeTextColor,
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
                    )
                },
                navigationIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_dragonball_svg),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .padding(start = 10.dp, end = 10.dp)
                    )
                },
                actions = {
                    DarkModeSwitch(
                        checked = isDarkTheme,
                        modifier = Modifier.padding(end = 12.dp),
                        onCheckedChanged = onToggleTheme
                    )
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Rounded.Search,
                            contentDescription = "Search",
                            tint = themeTextColor,
                            modifier = Modifier
                                .size(60.dp)
                                .padding(start = 5.dp, end = 5.dp)
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Default.MoreVert,
                            contentDescription = "More Actions",
                            tint = themeTextColor,
                            modifier = Modifier
                                .size(60.dp)
                                .padding(start = 5.dp, end = 5.dp)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
        Cards(isDarkTheme = isDarkTheme) // <- Aquí aparecerán tus 4 cards en 2 columnas
        }
    }
}