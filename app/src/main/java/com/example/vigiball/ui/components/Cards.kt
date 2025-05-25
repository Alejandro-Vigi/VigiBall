package com.example.vigiball.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.example.vigiball.ui.model.Character
import com.example.vigiball.ui.network.DragonBallApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun Cards(isDarkTheme: Boolean) {
    var characters by remember { mutableStateOf(emptyList<Character>()) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }
    var retryCount by remember { mutableStateOf(0) }
    var selectedCharacterId by rememberSaveable { mutableStateOf<String?>(null) }
    val selectedCharacter = characters.find { it.id == selectedCharacterId }

    LaunchedEffect(retryCount) {
        try {
            val response = withContext(Dispatchers.IO) {
                DragonBallApi.service.getCharacters()
            }
            characters = response.items.map {
                Character(
                    id = it.id.toString(),
                    name = it.name,
                    affiliation = it.affiliation,
                    imageUrl = it.image,
                    ki = it.ki,
                    maxKi = it.maxKi,
                    race = it.race,
                    gender = it.gender,
                    description = it.description
                )
            }
            error = null
        } catch (e: Exception) {
            error = if (e.message?.contains("No address associated with hostname") == true) {
                "No hay conexión a internet"
            } else {
                "Error al cargar los datos"
            }
        } finally {
            isLoading = false
        }
    }

    val cardColor = if (isDarkTheme) Color(0xFF252733) else Color(0xFFDFE1E3)
    val textColor = if (isDarkTheme) Color.White else Color.Black
    val errorColor = if (isDarkTheme) Color(0xFFFF6666) else Color(0xFFD32F2F)

    when {
        isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        error != null -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.Warning,
                    contentDescription = "Error",
                    tint = errorColor,
                    modifier = Modifier.size(60.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = error!!,
                    color = textColor,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        isLoading = true
                        retryCount++
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isDarkTheme) Color(0xFF89322B) else Color(0xFFEF4138),
                        contentColor = textColor
                    ),
                    modifier = Modifier.width(200.dp)
                ) {
                    Text(
                        "Reintentar", fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }
        }

        else -> LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(start = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            items(characters) { character ->
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = cardColor),
                    modifier = Modifier
                        .height(270.dp)
                        .fillMaxWidth()
                        .clickable { selectedCharacterId = character.id }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        AsyncImage(
                            model = character.imageUrl,
                            contentDescription = character.name,
                            modifier = Modifier
                                .height(180.dp)
                                .fillMaxWidth(),
                            contentScale = ContentScale.Fit
                        )

                        Text(
                            text = character.name,
                            fontSize = 20.sp,
                            color = textColor,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodyLarge,
                            maxLines = 2,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Text(
                            text = character.affiliation,
                            fontSize = 16.sp,
                            color = textColor,
                            style = MaterialTheme.typography.bodyMedium,
                            maxLines = 2
                        )
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
    if (selectedCharacter != null) {
        Dialog(
            onDismissRequest = { selectedCharacterId = null },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .background(cardColor, shape = RoundedCornerShape(16.dp))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 700.dp)
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    // Aquí va todo el contenido de la ventana
                    Text(
                        text = selectedCharacter!!.name,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    AsyncImage(
                        model = selectedCharacter!!.imageUrl,
                        contentDescription = selectedCharacter!!.name,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .height(240.dp)
                            .fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Afiliación: ${selectedCharacter!!.affiliation}",
                        fontSize = 18.sp,
                        color = textColor
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "KI: ${selectedCharacter!!.ki}",
                        fontSize = 18.sp,
                        color = textColor
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "KI Máximo: ${selectedCharacter!!.maxKi}",
                        fontSize = 18.sp,
                        color = textColor
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Raza: ${selectedCharacter!!.race}",
                        fontSize = 18.sp,
                        color = textColor
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Género: ${selectedCharacter!!.gender}",
                        fontSize = 18.sp,
                        color = textColor
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Descripción:\n${selectedCharacter!!.description}",
                        fontSize = 16.sp,
                        color = textColor,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Button(
                        onClick = { selectedCharacterId = null },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isDarkTheme) Color(0xFF89322B) else Color(0xFFEF4138),
                            contentColor = Color.White,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text("Cerrar", fontSize = 16.sp)
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}