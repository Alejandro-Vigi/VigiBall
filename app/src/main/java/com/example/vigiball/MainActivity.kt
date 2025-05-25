package com.example.vigiball

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.vigiball.ui.theme.VigiBallTheme
import kotlinx.coroutines.launch
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            var isDarkTheme by remember { mutableStateOf(false) }

            // Acceder a la ventana actual
            val view = LocalView.current
            if (!view.isInEditMode) {
                SideEffect {
                    val window = (view.context as Activity).window
                    val insetsController = WindowCompat.getInsetsController(window, view)

                    insetsController.isAppearanceLightStatusBars = !isDarkTheme
                }
            }

            VigiBallTheme(darkTheme = isDarkTheme) {
                MyApp(
                    isDarkTheme = isDarkTheme,
                    onToggleTheme = { isDarkTheme = it }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(
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
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            Text(
                text = if (isDarkTheme) "Modo Oscuro Activo" else "Modo Claro Activo",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun DarkModeSwitch(checked: Boolean, modifier: Modifier, onCheckedChanged: (Boolean) -> Unit) {

    val switchWidth = 80.dp
    val switchHeight = 30.dp
    val handleSize = 24.dp
    val handlePadding = 7.dp

    val valueToOffset = if (checked) 1f else 0f
    val offset = remember { Animatable(valueToOffset) }
    val scope = rememberCoroutineScope()

    val blueSky= Color(0xFF4478a9)
    val nightSky =  Color(0xFF333333)
    val borderColor = Color(0x40000000)

    DisposableEffect(checked) {
        if (offset.targetValue != valueToOffset) {
            scope.launch {
                offset.animateTo(valueToOffset, animationSpec = tween(300))
            }
        }
        onDispose { }
    }

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .width(switchWidth)
            .height(switchHeight)
            .clip(RoundedCornerShape(switchHeight))
            .background(lerp(blueSky, nightSky, offset.value))
            .border(3.dp, borderColor, RoundedCornerShape(switchHeight))
            .toggleable(
                value = checked,
                onValueChange = onCheckedChanged,
                role = Role.Switch,
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            )
    ) {
        val backgroundPainter = painterResource(R.drawable.background)
        Canvas(modifier = Modifier.fillMaxSize()) {
            with(backgroundPainter) {
                val scale = size.width / intrinsicSize.width
                val scaledHeight = intrinsicSize.height * scale
                translate(top = (size.height - scaledHeight) * (1f - offset.value)) {
                    draw(Size(size.width, scaledHeight))
                }
            }
        }

        Image(
            painter = painterResource(R.drawable.glow),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(switchWidth)
                .graphicsLayer {
                    scaleX = 1.2f
                    scaleY = scaleX
                    translationX = lerp(
                        -size.width * 0.5f + handlePadding.toPx() + handleSize.toPx() * 0.5f,
                        switchWidth.toPx() - size.width * 0.5f - handlePadding.toPx() - handleSize.toPx() * 0.5f,
                        offset.value
                    )
                }
        )

        Box(
            modifier = Modifier
                .padding(horizontal = handlePadding)
                .size(handleSize)
                .offset(x = (switchWidth - handleSize - handlePadding * 2f) * offset.value)
                .paint(painterResource(R.drawable.sun))
                .clip(CircleShape)
        ) {
            Image(
                painter = painterResource(R.drawable.moon),
                contentDescription = null,
                modifier = Modifier
                    .size(handleSize)
                    .graphicsLayer {
                        translationX = size.width * (1f - offset.value)
                    }
            )
        }
    }
}