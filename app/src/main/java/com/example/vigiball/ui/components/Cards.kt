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
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.example.vigiball.ui.model.Character
import com.example.vigiball.ui.model.Transformation
import com.example.vigiball.ui.network.DragonBallApi
import com.example.vigiball.ui.theme.Black
import com.example.vigiball.ui.theme.DarkBlue
import com.example.vigiball.ui.theme.DarkRed
import com.example.vigiball.ui.theme.LightGray
import com.example.vigiball.ui.theme.LightRed
import com.example.vigiball.ui.theme.SuperDarkRed
import com.example.vigiball.ui.theme.SuperLightRed
import com.example.vigiball.ui.theme.White
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Cards(
    isDarkTheme: Boolean,
    searchQuery: String
) {
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }
    var retryCount by remember { mutableIntStateOf(0) }
    var selectedCharacter by remember { mutableStateOf<Character?>(null) }
    var isLoadingDialog by remember { mutableStateOf(false) }
    var allCharacters by remember { mutableStateOf(emptyList<Character>()) }
    var filteredCharacters by remember { mutableStateOf(emptyList<Character>()) }
    val cardColor = if (isDarkTheme) DarkBlue else LightGray
    val textColor = if (isDarkTheme) White else Black
    val errorColor = if (isDarkTheme) LightRed else DarkRed

    LaunchedEffect(retryCount) {
        try {
            val response = withContext(Dispatchers.IO) {
                DragonBallApi.service.getCharacters()
            }
            allCharacters = response.items.map {
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
            filteredCharacters = if (searchQuery.isBlank()) {
                allCharacters
            } else {
                allCharacters.filter { character ->
                    character.name.contains(searchQuery, ignoreCase = true)
                }
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

    LaunchedEffect(searchQuery) {
        filteredCharacters = if (searchQuery.isBlank()) {
            allCharacters
        } else {
            allCharacters.filter { character ->
                character.name.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    when {
        isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
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
                        containerColor = if (isDarkTheme) SuperDarkRed else SuperLightRed,
                        contentColor = textColor
                    ),
                    modifier = Modifier.width(200.dp)
                ) {
                    Text(
                        "Reintentar", fontSize = 16.sp,
                        color = White
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
            items(filteredCharacters) { character ->
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = cardColor),
                    modifier = Modifier
                        .height(270.dp)
                        .fillMaxWidth()
                        .clickable {
                            selectedCharacter = character
                            isLoadingDialog = true
                        }
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

    // Diálogo
    selectedCharacter?.let { currentCharacter ->
        val characterState by produceState<Character?>(
            initialValue = currentCharacter,
            key1 = currentCharacter,
            producer = {
                try {
                    val details = withContext(Dispatchers.IO) {
                        DragonBallApi.service.getCharacterDetails(currentCharacter.id.toInt())
                    }
                    value = currentCharacter.copy(
                        name = details.name,
                        affiliation = details.affiliation,
                        imageUrl = details.image,
                        ki = details.ki,
                        maxKi = details.maxKi,
                        race = details.race,
                        gender = details.gender,
                        description = details.description,
                        transformations = details.transformations?.map {
                            Transformation(
                                id = it.id.toString(),
                                name = it.name,
                                image = it.image,
                                ki = it.ki
                            )
                        } ?: emptyList()
                    )
                } catch (_: Exception) {
                    value = currentCharacter
                } finally {
                    isLoadingDialog = false
                }
            }
        )

        Dialog(
            onDismissRequest = {
                selectedCharacter = null
                isLoadingDialog = false
            },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            key(characterState?.id ?: "") {
                if (isLoadingDialog) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                            .height(200.dp)
                            .padding(vertical = 40.dp)
                            .background(cardColor, shape = RoundedCornerShape(16.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                            .background(cardColor, shape = RoundedCornerShape(16.dp))
                    ) {
                        IconButton(
                            onClick = { selectedCharacter = null },
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .zIndex(1f)
                                .padding(end = 15.dp, top = 15.dp)
                                .size(30.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Close,
                                contentDescription = "Close",
                                tint = textColor,
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        characterState?.let { character ->
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(max = 700.dp)
                                    .padding(16.dp)
                                    .verticalScroll(rememberScrollState()),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Top
                            ) {
                                Text(
                                    text = character.name,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = textColor
                                )

                                Spacer(modifier = Modifier.height(12.dp))

                                AsyncImage(
                                    model = character.imageUrl,
                                    contentDescription = character.name,
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                        .height(240.dp)
                                        .fillMaxWidth(),
                                )

                                Spacer(modifier = Modifier.height(12.dp))

                                Text(
                                    "Afiliación: ${character.affiliation}",
                                    fontSize = 18.sp,
                                    color = textColor
                                )

                                Text(
                                    "KI: ${character.ki}",
                                    fontSize = 18.sp,
                                    color = textColor
                                )

                                Text(
                                    "KI Máximo: ${character.maxKi}",
                                    fontSize = 18.sp,
                                    color = textColor
                                )

                                Text(
                                    "Raza: ${character.race}",
                                    fontSize = 18.sp,
                                    color = textColor
                                )

                                Text(
                                    "Género: ${character.gender}",
                                    fontSize = 18.sp,
                                    color = textColor
                                )

                                Spacer(modifier = Modifier.height(12.dp))

                                Text(
                                    text = "Descripción:\n${character.description}",
                                    fontSize = 16.sp,
                                    color = textColor,
                                    textAlign = TextAlign.Justify,
                                    modifier = Modifier
                                        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = "Transformaciones:",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = textColor
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                if (character.transformations.isNotEmpty()) {
                                    FlowRow(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceEvenly,
                                        verticalArrangement = Arrangement.Center,
                                        maxItemsInEachRow = 2
                                    ) {
                                        character.transformations.forEach { transformation ->
                                            Column(
                                                modifier = Modifier
                                                    .width(150.dp)
                                                    .padding(8.dp),
                                                horizontalAlignment = Alignment.CenterHorizontally
                                            ) {
                                                Text(
                                                    text = transformation.name,
                                                    fontSize = 16.sp,
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = textColor,
                                                    textAlign = TextAlign.Center,
                                                    modifier = Modifier.fillMaxWidth()
                                                )

                                                AsyncImage(
                                                    model = transformation.image,
                                                    contentDescription = transformation.name,
                                                    modifier = Modifier
                                                        .height(120.dp)
                                                        .fillMaxWidth(),
                                                    contentScale = ContentScale.Fit
                                                )

                                                Text(
                                                    text = "KI: ${transformation.ki}",
                                                    color = textColor,
                                                    textAlign = TextAlign.Center,
                                                    modifier = Modifier.fillMaxWidth()
                                                )
                                            }
                                        }
                                    }
                                } else {
                                    Spacer(modifier = Modifier.height(3.dp))
                                    Text(
                                        text = "Este personaje no tiene transformaciones.",
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center,
                                        fontSize = 16.sp,
                                        color = textColor
                                    )
                                }

                                Spacer(modifier = Modifier.height(15.dp))

                                Button(
                                    onClick = { selectedCharacter = null },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = if (isDarkTheme) SuperDarkRed else SuperLightRed,
                                        contentColor = Color.White,
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Text("Cerrar", fontSize = 16.sp)
                                }
                                Spacer(modifier = Modifier.height(5.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}