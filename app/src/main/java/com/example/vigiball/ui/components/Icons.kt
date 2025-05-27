package com.example.vigiball.ui.components

import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.example.vigiball.ui.theme.Black

/*
 Spanish:
 Estos son los íconos personalizados para redes sociales que uso en la app,
 están hechos como ImageVector para que se vean bien en cualquier tamaño,
 cada uno tiene su path con las instrucciones de dibujo, el de Instagram
 tiene ese detalle de la cámara y el degradado característico, el de LinkedIn
 es más cuadrado con su letra 'in' y el de GitHub tiene ese gato que todos conocemos,
 lo bueno es que se cachean para no crearlos cada vez que se usan.

 English:
 These are custom social media icons for the app, made as ImageVector to look
 sharp at any size, each has its path with drawing instructions, the Instagram
 one has that camera detail and characteristic gradient, LinkedIn is more squared
 with its 'in' letter, and GitHub has that cat we all know, the cool part is they're
 cached so they're not recreated every time they're used.
*/

// Icon instagram
val ic_instagram: ImageVector
    get() {
        if (ic_instagram_private != null) {
            return ic_instagram_private!!
        }
        ic_instagram_private = ImageVector.Builder(
            name = "Instagram",
            defaultWidth = 16.dp,
            defaultHeight = 16.dp,
            viewportWidth = 16f,
            viewportHeight = 16f
        ).apply {
            path(
                fill = SolidColor(Black),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(8f, 0f)
                curveTo(5.829f, 0f, 5.556f, 0.01f, 4.703f, 0.048f)
                curveTo(3.85f, 0.088f, 3.269f, 0.222f, 2.76f, 0.42f)
                arcToRelative(3.9f, 3.9f, 0f, isMoreThanHalf = false, isPositiveArc = false, -1.417f, 0.923f)
                arcTo(3.9f, 3.9f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0.42f, 2.76f)
                curveTo(0.222f, 3.268f, 0.087f, 3.85f, 0.048f, 4.7f)
                curveTo(0.01f, 5.555f, 0f, 5.827f, 0f, 8.001f)
                curveToRelative(0f, 2.172f, 0.01f, 2.444f, 0.048f, 3.297f)
                curveToRelative(0.04f, 0.852f, 0.174f, 1.433f, 0.372f, 1.942f)
                curveToRelative(0.205f, 0.526f, 0.478f, 0.972f, 0.923f, 1.417f)
                curveToRelative(0.444f, 0.445f, 0.89f, 0.719f, 1.416f, 0.923f)
                curveToRelative(0.51f, 0.198f, 1.09f, 0.333f, 1.942f, 0.372f)
                curveTo(5.555f, 15.99f, 5.827f, 16f, 8f, 16f)
                reflectiveCurveToRelative(2.444f, -0.01f, 3.298f, -0.048f)
                curveToRelative(0.851f, -0.04f, 1.434f, -0.174f, 1.943f, -0.372f)
                arcToRelative(3.9f, 3.9f, 0f, isMoreThanHalf = false, isPositiveArc = false, 1.416f, -0.923f)
                curveToRelative(0.445f, -0.445f, 0.718f, -0.891f, 0.923f, -1.417f)
                curveToRelative(0.197f, -0.509f, 0.332f, -1.09f, 0.372f, -1.942f)
                curveTo(15.99f, 10.445f, 16f, 10.173f, 16f, 8f)
                reflectiveCurveToRelative(-0.01f, -2.445f, -0.048f, -3.299f)
                curveToRelative(-0.04f, -0.851f, -0.175f, -1.433f, -0.372f, -1.941f)
                arcToRelative(3.9f, 3.9f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.923f, -1.417f)
                arcTo(3.9f, 3.9f, 0f, isMoreThanHalf = false, isPositiveArc = false, 13.24f, 0.42f)
                curveToRelative(-0.51f, -0.198f, -1.092f, -0.333f, -1.943f, -0.372f)
                curveTo(10.443f, 0.01f, 10.172f, 0f, 7.998f, 0f)
                close()
                moveToRelative(-0.717f, 1.442f)
                horizontalLineToRelative(0.718f)
                curveToRelative(2.136f, 0f, 2.389f, 0.007f, 3.232f, 0.046f)
                curveToRelative(0.78f, 0.035f, 1.204f, 0.166f, 1.486f, 0.275f)
                curveToRelative(0.373f, 0.145f, 0.64f, 0.319f, 0.92f, 0.599f)
                reflectiveCurveToRelative(0.453f, 0.546f, 0.598f, 0.92f)
                curveToRelative(0.11f, 0.281f, 0.24f, 0.705f, 0.275f, 1.485f)
                curveToRelative(0.039f, 0.843f, 0.047f, 1.096f, 0.047f, 3.231f)
                reflectiveCurveToRelative(-0.008f, 2.389f, -0.047f, 3.232f)
                curveToRelative(-0.035f, 0.78f, -0.166f, 1.203f, -0.275f, 1.485f)
                arcToRelative(2.5f, 2.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.599f, 0.919f)
                curveToRelative(-0.28f, 0.28f, -0.546f, 0.453f, -0.92f, 0.598f)
                curveToRelative(-0.28f, 0.11f, -0.704f, 0.24f, -1.485f, 0.276f)
                curveToRelative(-0.843f, 0.038f, -1.096f, 0.047f, -3.232f, 0.047f)
                reflectiveCurveToRelative(-2.39f, -0.009f, -3.233f, -0.047f)
                curveToRelative(-0.78f, -0.036f, -1.203f, -0.166f, -1.485f, -0.276f)
                arcToRelative(2.5f, 2.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.92f, -0.598f)
                arcToRelative(2.5f, 2.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.6f, -0.92f)
                curveToRelative(-0.109f, -0.281f, -0.24f, -0.705f, -0.275f, -1.485f)
                curveToRelative(-0.038f, -0.843f, -0.046f, -1.096f, -0.046f, -3.233f)
                reflectiveCurveToRelative(0.008f, -2.388f, 0.046f, -3.231f)
                curveToRelative(0.036f, -0.78f, 0.166f, -1.204f, 0.276f, -1.486f)
                curveToRelative(0.145f, -0.373f, 0.319f, -0.64f, 0.599f, -0.92f)
                reflectiveCurveToRelative(0.546f, -0.453f, 0.92f, -0.598f)
                curveToRelative(0.282f, -0.11f, 0.705f, -0.24f, 1.485f, -0.276f)
                curveToRelative(0.738f, -0.034f, 1.024f, -0.044f, 2.515f, -0.045f)
                close()
                moveToRelative(4.988f, 1.328f)
                arcToRelative(0.96f, 0.96f, 0f, isMoreThanHalf = true, isPositiveArc = false, 0f, 1.92f)
                arcToRelative(0.96f, 0.96f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0f, -1.92f)
                moveToRelative(-4.27f, 1.122f)
                arcToRelative(4.109f, 4.109f, 0f, isMoreThanHalf = true, isPositiveArc = false, 0f, 8.217f)
                arcToRelative(4.109f, 4.109f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0f, -8.217f)
                moveToRelative(0f, 1.441f)
                arcToRelative(2.667f, 2.667f, 0f, isMoreThanHalf = true, isPositiveArc = true, 0f, 5.334f)
                arcToRelative(2.667f, 2.667f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, -5.334f)
            }
        }.build()
        return ic_instagram_private!!
    }

private var ic_instagram_private: ImageVector? = null

// Icon LinkedIn
val ic_linkedin: ImageVector
    get() {
        if (ic_linkedin_private != null) {
            return ic_linkedin_private!!
        }
        ic_linkedin_private = ImageVector.Builder(
            name = "Linkedin",
            defaultWidth = 15.dp,
            defaultHeight = 15.dp,
            viewportWidth = 15f,
            viewportHeight = 15f
        ).apply {
            path(
                fill = SolidColor(Black),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(2f, 1f)
                curveTo(1.4477f, 1f, 1f, 1.4477f, 1f, 2f)
                verticalLineTo(13f)
                curveTo(1f, 13.5523f, 1.4477f, 14f, 2f, 14f)
                horizontalLineTo(13f)
                curveTo(13.5523f, 14f, 14f, 13.5523f, 14f, 13f)
                verticalLineTo(2f)
                curveTo(14f, 1.4477f, 13.5523f, 1f, 13f, 1f)
                horizontalLineTo(2f)
                close()
                moveTo(3.05f, 6f)
                horizontalLineTo(4.95f)
                verticalLineTo(12f)
                horizontalLineTo(3.05f)
                verticalLineTo(6f)
                close()
                moveTo(5.075f, 4.005f)
                curveTo(5.075f, 4.5987f, 4.5937f, 5.08f, 4f, 5.08f)
                curveTo(3.4063f, 5.08f, 2.925f, 4.5987f, 2.925f, 4.005f)
                curveTo(2.925f, 3.4113f, 3.4063f, 2.93f, 4f, 2.93f)
                curveTo(4.5937f, 2.93f, 5.075f, 3.4113f, 5.075f, 4.005f)
                close()
                moveTo(12f, 8.35713f)
                curveTo(12f, 6.5521f, 10.8334f, 5.8503f, 9.6745f, 5.8503f)
                curveTo(9.295f, 5.8316f, 8.9172f, 5.9112f, 8.5787f, 6.0811f)
                curveTo(8.3217f, 6.2101f, 8.0526f, 6.5052f, 7.8452f, 7.0185f)
                horizontalLineTo(7.79179f)
                verticalLineTo(6.00044f)
                horizontalLineTo(6f)
                verticalLineTo(12.0047f)
                horizontalLineTo(7.90616f)
                verticalLineTo(8.8112f)
                curveTo(7.8786f, 8.4841f, 7.9833f, 8.0614f, 8.1974f, 7.8099f)
                curveTo(8.4116f, 7.5583f, 8.7179f, 7.4982f, 8.9502f, 7.4677f)
                horizontalLineTo(9.02258f)
                curveTo(9.6287f, 7.4677f, 10.0786f, 7.843f, 10.0786f, 8.7887f)
                verticalLineTo(12.0047f)
                horizontalLineTo(11.9847f)
                lineTo(12f, 8.35713f)
                close()
            }
        }.build()
        return ic_linkedin_private!!
    }

private var ic_linkedin_private: ImageVector? = null


// Icon Github
val ic_github: ImageVector
    get() {
        if (ic_private_github != null) {
            return ic_private_github!!
        }
        ic_private_github = ImageVector.Builder(
            name = "Github",
            defaultWidth = 16.dp,
            defaultHeight = 16.dp,
            viewportWidth = 16f,
            viewportHeight = 16f
        ).apply {
            path(
                fill = SolidColor(Black),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(8f, 0f)
                curveTo(3.58f, 0f, 0f, 3.58f, 0f, 8f)
                curveToRelative(0f, 3.54f, 2.29f, 6.53f, 5.47f, 7.59f)
                curveToRelative(0.4f, 0.07f, 0.55f, -0.17f, 0.55f, -0.38f)
                curveToRelative(0f, -0.19f, -0.01f, -0.82f, -0.01f, -1.49f)
                curveToRelative(-2.01f, 0.37f, -2.53f, -0.49f, -2.69f, -0.94f)
                curveToRelative(-0.09f, -0.23f, -0.48f, -0.94f, -0.82f, -1.13f)
                curveToRelative(-0.28f, -0.15f, -0.68f, -0.52f, -0.01f, -0.53f)
                curveToRelative(0.63f, -0.01f, 1.08f, 0.58f, 1.23f, 0.82f)
                curveToRelative(0.72f, 1.21f, 1.87f, 0.87f, 2.33f, 0.66f)
                curveToRelative(0.07f, -0.52f, 0.28f, -0.87f, 0.51f, -1.07f)
                curveToRelative(-1.78f, -0.2f, -3.64f, -0.89f, -3.64f, -3.95f)
                curveToRelative(0f, -0.87f, 0.31f, -1.59f, 0.82f, -2.15f)
                curveToRelative(-0.08f, -0.2f, -0.36f, -1.02f, 0.08f, -2.12f)
                curveToRelative(0f, 0f, 0.67f, -0.21f, 2.2f, 0.82f)
                curveToRelative(0.64f, -0.18f, 1.32f, -0.27f, 2f, -0.27f)
                reflectiveCurveToRelative(1.36f, 0.09f, 2f, 0.27f)
                curveToRelative(1.53f, -1.04f, 2.2f, -0.82f, 2.2f, -0.82f)
                curveToRelative(0.44f, 1.1f, 0.16f, 1.92f, 0.08f, 2.12f)
                curveToRelative(0.51f, 0.56f, 0.82f, 1.27f, 0.82f, 2.15f)
                curveToRelative(0f, 3.07f, -1.87f, 3.75f, -3.65f, 3.95f)
                curveToRelative(0.29f, 0.25f, 0.54f, 0.73f, 0.54f, 1.48f)
                curveToRelative(0f, 1.07f, -0.01f, 1.93f, -0.01f, 2.2f)
                curveToRelative(0f, 0.21f, 0.15f, 0.46f, 0.55f, 0.38f)
                arcTo(8.01f, 8.01f, 0f, isMoreThanHalf = false, isPositiveArc = false, 16f, 8f)
                curveToRelative(0f, -4.42f, -3.58f, -8f, -8f, -8f)
            }
        }.build()
        return ic_private_github!!
    }

private var ic_private_github: ImageVector? = null
