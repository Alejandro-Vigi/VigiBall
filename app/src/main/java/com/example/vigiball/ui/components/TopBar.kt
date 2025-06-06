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
import com.example.vigiball.ui.theme.Black
import com.example.vigiball.ui.theme.DarkBlue
import com.example.vigiball.ui.theme.Gray
import com.example.vigiball.ui.theme.LightBlue
import com.example.vigiball.ui.theme.LightGray
import com.example.vigiball.ui.theme.MediumDarkBlue
import com.example.vigiball.ui.theme.SuperDarkBlue
import com.example.vigiball.ui.theme.SuperDarkRed
import com.example.vigiball.ui.theme.SuperLightRed
import com.example.vigiball.ui.theme.White

/*
 Spanish:
 Esta es la barra superior de la app que tiene el logo, título y botones de acción,
 incluye el switch de tema oscuro que hicimos antes, un botón de búsqueda y un menú
 desplegable con opciones para compartir la app y ver info del desarrollador, cuando
 haces click en "About" aparece un diálogo con mis redes sociales y datos de la universidad,
 también tiene un buscador que filtra los personajes, se adapta al tema claro/oscuro y tiene
 animaciones fluidas.

 English:
 This is the app's top bar with logo, title and action buttons, includes our dark theme
 switch, a search button and dropdown menu with options to share the app and developer info,
 when you click "About" a cool dialog appears with my social media and university info, it
 also has a search feature that filters characters, everything adapts to light/dark theme
 and has smooth animations.
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    isDarkTheme: Boolean,
    onToggleTheme: (Boolean) -> Unit
) {
    val themeTextColor = if (isDarkTheme) White else Black
    val topBarColor = if (isDarkTheme) SuperDarkBlue else White
    val backgroundColor = if(isDarkTheme) SuperDarkBlue else White
    var showMenu by remember { mutableStateOf(false) }
    var showAboutDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    var searchQuery by remember { mutableStateOf("") }
    var isSearchActive by remember { mutableStateOf(false) }


    fun shareAppLink() {
        val intent = Intent(Intent.ACTION_VIEW, context.getString(R.string.link_repository).toUri())
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
                        text = context.getString(R.string.app_name),
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
                            contentDescription = context.getString(R.string.search),
                            tint = themeTextColor,
                            modifier = Modifier
                                .size(60.dp)
                                .padding(start = 5.dp, end = 5.dp)
                        )
                    }

                    Box {
                        IconButton(onClick = { showMenu = !showMenu }) {
                            Icon(
                                Icons.Default.MoreVert,
                                contentDescription = context.getString(R.string.more_actions),
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
                                .background(if (isDarkTheme) DarkBlue else White)
                                .width(160.dp)
                        ) {
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = context.getString(R.string.share),
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
                                        contentDescription = context.getString(R.string.share),
                                        tint = themeTextColor,
                                        modifier = Modifier.padding(start = 10.dp)
                                    )
                                }
                            )

                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = context.getString(R.string.about),
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
                                        contentDescription = context.getString(R.string.about),
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
                                contentDescription = context.getString(R.string.close),
                                tint = if (isDarkTheme) White else Black,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = context.getString(R.string.about_app),
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Image(
                                painter = painterResource(id = R.drawable.ic_dragonball_svg),
                                contentDescription = context.getString(R.string.logo_app),
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
                            text = context.getString(R.string.developer),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W600
                        )
                        Text(
                            text = context.getString(R.string.name_developer),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W400
                        )
                        Spacer(modifier = Modifier.height(7.dp))
                        Text(
                            text = context.getString(R.string.university),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W600
                        )
                        Spacer(modifier = Modifier.height(7.dp))
                        Text(
                            text = context.getString(R.string.version),
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
                            color = if (isDarkTheme) Black else Gray
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
                                        context.getString(R.string.link_linkedin).toUri())
                                    context.startActivity(intent)
                                },
                                modifier = Modifier.size(50.dp)
                            ) {
                                Icon(
                                    imageVector = ic_instagram,
                                    modifier = Modifier.size(40.dp),
                                    contentDescription = context.getString(R.string.instagram),
                                    tint = if (isDarkTheme) White else Black
                                )
                            }
                            Spacer(modifier = Modifier.width(25.dp))
                            IconButton(
                                onClick = {
                                    val intent = Intent(Intent.ACTION_VIEW,
                                        context.getString(R.string.link_linkedin).toUri())
                                    context.startActivity(intent)
                                },
                                modifier = Modifier.size(50.dp)
                            ) {
                                Icon(
                                    imageVector = ic_linkedin,
                                    modifier = Modifier.size(40.dp),
                                    contentDescription = context.getString(R.string.linkedin),
                                    tint = if (isDarkTheme) White else Black
                                )
                            }
                            Spacer(modifier = Modifier.width(25.dp))
                            IconButton(
                                onClick = {
                                    val intent = Intent(Intent.ACTION_VIEW,
                                        context.getString(R.string.link_github).toUri())
                                    context.startActivity(intent)
                                },
                                modifier = Modifier.size(50.dp)
                            ) {
                                Icon(
                                    imageVector = ic_github,
                                    modifier = Modifier.size(40.dp),
                                    contentDescription = context.getString(R.string.github),
                                    tint = if (isDarkTheme) White else Black
                                )
                            }
                        }
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = { showAboutDialog = false },
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = if (isDarkTheme) SuperDarkRed else SuperLightRed,
                            contentColor = White
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(context.getString(R.string.cerrar))
                    }
                },
                containerColor = if (isDarkTheme) DarkBlue else White,
                textContentColor = if (isDarkTheme) White else Black,
                titleContentColor = if (isDarkTheme) White else Black
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
                                contentDescription = context.getString(R.string.close),
                                tint = if (isDarkTheme) White else Black,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Text(
                            context.getString(R.string.search_character),
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
                            label = { Text(context.getString(R.string.name_character)) },
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
                                containerColor = if (isDarkTheme) DarkBlue else LightGray
                            ),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(context.getString(R.string.clear),
                                color = if (isDarkTheme) White else Black)
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Button(
                            onClick = {
                                isSearchActive = false
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isDarkTheme) MediumDarkBlue else LightBlue
                            ),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(context.getString(R.string.buscar),
                                color = if (isDarkTheme) White else Black)
                        }
                    }
                }
            )
        }
    }
}