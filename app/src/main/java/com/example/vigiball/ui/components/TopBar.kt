package com.example.vigiball.ui.components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.vigiball.R
import androidx.core.net.toUri
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    isDarkTheme: Boolean,
    onToggleTheme: (Boolean) -> Unit
) {
    val themeTextColor = if (isDarkTheme) Color.White else Color.Black
    val topBarColor = if (isDarkTheme) Color(0xFF181c25) else Color(0xFFFFFFFF)
    val backgroundColor = if(isDarkTheme) Color(0xFF181c25) else Color(0xFFFFFFFF)
    var showMenu by remember { mutableStateOf(false) }
    var showAboutDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    var searchQuery by remember { mutableStateOf("") }
    var isSearchActive by remember { mutableStateOf(false) }


    fun shareAppLink() {
        val intent = Intent(Intent.ACTION_VIEW, "https://github.com/Alejandro-Vigi/VigiBall".toUri())
        context.startActivity(intent)
    }

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
                    IconButton(onClick = { isSearchActive = true }) {
                        Icon(
                            Icons.Rounded.Search,
                            contentDescription = "Search",
                            tint = themeTextColor,
                            modifier = Modifier
                                .size(60.dp)
                                .padding(start = 5.dp, end = 5.dp)
                        )
                    }

                    // Menú de opciones
                    Box {
                        IconButton(onClick = { showMenu = !showMenu }) {
                            Icon(
                                Icons.Default.MoreVert,
                                contentDescription = "More Actions",
                                tint = themeTextColor,
                                modifier = Modifier
                                    .size(60.dp)
                                    .padding(start = 5.dp, end = 5.dp)
                            )
                        }

                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false },
                            modifier = Modifier
                                .background(if (isDarkTheme) Color(0xFF252733) else Color.White)
                                .width(160.dp)
                        ) {
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = "Compartir",
                                        color = themeTextColor,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Medium,
                                        modifier = Modifier.padding(start = 5.dp)
                                    )
                                },
                                onClick = {
                                    shareAppLink()
                                    showMenu = false
                                },
                                leadingIcon = {
                                    Icon(
                                        Icons.Rounded.Share,
                                        contentDescription = "Compartir",
                                        tint = themeTextColor,
                                        modifier = Modifier.padding(start = 10.dp)
                                    )
                                }
                            )

                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = "Acerca de",
                                        color = themeTextColor,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Medium,
                                        modifier = Modifier.padding(start = 5.dp)
                                    )
                                },
                                onClick = {
                                    showAboutDialog = true
                                    showMenu = false
                                },
                                leadingIcon = {
                                    Icon(
                                        Icons.Rounded.Info,
                                        contentDescription = "Acerca de",
                                        tint = themeTextColor,
                                        modifier = Modifier.padding(start = 10.dp)
                                    )
                                }
                            )
                        }
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
            Cards(
                isDarkTheme = isDarkTheme,
                searchQuery = searchQuery
            )
        }
        if (showAboutDialog) {
            AlertDialog(
                onDismissRequest = { showAboutDialog = false },
                title = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        IconButton(
                            onClick = { showAboutDialog = false },
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .zIndex(1f)
                                .offset(10.dp)
                                .size(30.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Close,
                                contentDescription = "Close",
                                tint = if (isDarkTheme) Color.White else Color.Black,
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        // Contenido del título (texto + imagen)
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                "Acerca de VigiBall",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Image(
                                painter = painterResource(id = R.drawable.ic_dragonball_svg),
                                contentDescription = "Logo de la aplicación",
                                modifier = Modifier
                                    .size(100.dp)
                                    .padding(top = 10.dp)
                            )
                        }
                    }
                },
                text = {
                    Column{
                        Text(
                            text = "Desarrollador",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W600
                        )
                        Text(
                            text = "Vigi Garduño Marco Alejandro",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W400
                        )
                        Spacer(modifier = Modifier.height(7.dp))
                        Text(
                            text = "Facultad de Ingeniería - 2025 ©",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W600
                        )
                        Spacer(modifier = Modifier.height(7.dp))
                        Text(
                            text = "Versión: 1.0",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W600
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxWidth(),
                            thickness = 1.dp,
                            color = if (isDarkTheme) Color.Black else Color.Gray
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = {
                                    val intent = Intent(Intent.ACTION_VIEW,
                                        "http://instagram.com/alejandro_vigi".toUri())
                                    context.startActivity(intent)
                                },
                                modifier = Modifier.size(50.dp)
                            ) {
                                Icon(
                                    imageVector = ic_instagram,
                                    modifier = Modifier.size(40.dp),
                                    contentDescription = "Instagram",
                                    tint = if (isDarkTheme) Color.White else Color.Black
                                )
                            }
                            Spacer(modifier = Modifier.width(25.dp))
                            IconButton(
                                onClick = {
                                    val intent = Intent(Intent.ACTION_VIEW,
                                        "https://www.linkedin.com/in/alejandro-vigi/".toUri())
                                    context.startActivity(intent)
                                },
                                modifier = Modifier.size(50.dp)
                            ) {
                                Icon(
                                    imageVector = ic_linkedin,
                                    modifier = Modifier.size(40.dp),
                                    contentDescription = "Linkedin",
                                    tint = if (isDarkTheme) Color.White else Color.Black
                                )
                            }
                            Spacer(modifier = Modifier.width(25.dp))
                            IconButton(
                                onClick = {
                                    val intent = Intent(Intent.ACTION_VIEW,
                                        "https://github.com/Alejandro-Vigi".toUri())
                                    context.startActivity(intent)
                                },
                                modifier = Modifier.size(50.dp)
                            ) {
                                Icon(
                                    imageVector = ic_github,
                                    modifier = Modifier.size(40.dp),
                                    contentDescription = "Instagram",
                                    tint = if (isDarkTheme) Color.White else Color.Black
                                )
                            }
                        }
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = { showAboutDialog = false },
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = if (isDarkTheme) Color(0xFF89322B) else Color(0xFFEF4138),
                            contentColor = Color.White
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Cerrar")
                    }
                },
                containerColor = if (isDarkTheme) Color(0xFF252733) else Color.White,
                textContentColor = if (isDarkTheme) Color.White else Color.Black,
                titleContentColor = if (isDarkTheme) Color.White else Color.Black
            )
        }
        if (isSearchActive) {
            AlertDialog(
                onDismissRequest = { isSearchActive = false },
                containerColor = backgroundColor,
                title = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        IconButton(
                            onClick = { isSearchActive = false },
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .zIndex(1f)
                                .offset(10.dp)
                                .size(30.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Close,
                                contentDescription = "Close",
                                tint = if (isDarkTheme) Color.White else Color.Black,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Text(
                            "Buscar Personaje",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                },
                text = {
                    Column {
                        OutlinedTextField(
                            value = searchQuery,
                            onValueChange = { searchQuery = it },
                            label = { Text("Nombre del personaje") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                    }
                },
                confirmButton = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = {
                                searchQuery = ""
                                isSearchActive = false
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isDarkTheme) Color(0xFF252733) else Color(0xFFDFE1E3)
                            ),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Limpiar",
                                color = if (isDarkTheme) Color.White else Color.Black)
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Button(
                            onClick = {
                                isSearchActive = false
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isDarkTheme) Color(0xFF3b496f) else Color(0xFF90b0e5)
                            ),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Buscar",
                                color = if (isDarkTheme) Color.White else Color.Black,)
                        }
                    }
                }
            )
        }
    }
}