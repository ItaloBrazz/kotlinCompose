package com.example.businesscard.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun BusinessCardTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = androidx.compose.material3.lightColorScheme(
            primary = Color(0xFF3DDC84),
            secondary = Color(0xFFD8EAE3)
        ),
        typography = Typography(),
        content = content
    )
}
