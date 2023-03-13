package com.mutkuensert.fitnesspursuit.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

class AppTypography(
    val defaultFontFamily: FontFamily = FontFamily.SansSerif,
    val h1: TextStyle = Typography.h1,
    val h2: TextStyle = Typography.h2,
    val h3: TextStyle = Typography.h3,
    val h4: TextStyle = Typography.h4,
    val h5: TextStyle = Typography.h5,
    val h6: TextStyle = Typography.h6,
    val subtitle1: TextStyle = Typography.subtitle1,
    val subtitle2: TextStyle = Typography.subtitle2,
    val body1: TextStyle = Typography.body1,
    val body2: TextStyle = Typography.body2,
    val bodyM: TextStyle,
    val button: TextStyle = Typography.button,
    val caption: TextStyle = Typography.caption,
    val overline: TextStyle = Typography.overline
)

val MaterialTheme.appTypography: AppTypography by lazy {
    AppTypography(
        defaultFontFamily = FontFamily.Default,
        h1 = TextStyle(
            fontWeight = FontWeight.Light,
            fontSize = 96.sp,
            letterSpacing = (-1.5).sp
        ),
        h2 = TextStyle(
            fontWeight = FontWeight.Light,
            fontSize = 60.sp,
            letterSpacing = (-0.5).sp
        ),
        h3 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 48.sp,
            letterSpacing = 0.sp
        ),
        h4 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 34.sp,
            letterSpacing = 0.25.sp
        ),
        h5 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
            letterSpacing = 0.sp
        ),
        h6 = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            letterSpacing = 0.15.sp
        ),
        subtitle1 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            letterSpacing = 0.15.sp
        ),
        subtitle2 = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            letterSpacing = 0.1.sp
        ),
        body1 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            letterSpacing = 0.5.sp
        ),
        body2 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            letterSpacing = 0.25.sp
        ),
        bodyM = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.W400,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            color = TextColors.primaryDark
        ),
        button = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            letterSpacing = 1.25.sp
        ),
        caption = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            letterSpacing = 0.4.sp
        ),
        overline = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
            letterSpacing = 1.5.sp
        )
    )
}

// Set of Material typography styles to start with
val Typography = Typography(
    defaultFontFamily = FontFamily.SansSerif,
    h1 = MaterialTheme.appTypography.h1,
    h2 = MaterialTheme.appTypography.h2,
    h3 = MaterialTheme.appTypography.h3,
    h4 = MaterialTheme.appTypography.h4,
    h5 = MaterialTheme.appTypography.h5,
    h6 = MaterialTheme.appTypography.h6,
    subtitle1 = MaterialTheme.appTypography.subtitle1,
    subtitle2 = MaterialTheme.appTypography.subtitle2,
    body1 = MaterialTheme.appTypography.body1,
    body2 = MaterialTheme.appTypography.body2,
    button = MaterialTheme.appTypography.button,
    caption = MaterialTheme.appTypography.caption,
    overline = MaterialTheme.appTypography.overline
)
