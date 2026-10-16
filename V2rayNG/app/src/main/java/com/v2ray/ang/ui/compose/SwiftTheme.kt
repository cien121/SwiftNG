package com.v2ray.ang.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// SwiftNG: purple-red gradient theme, shared by all pages
val SwiftAppGradient = Brush.verticalGradient(
    listOf(
        Color(0xFF3D1A5C),
        Color(0xFF4A1535),
        Color(0xFF220A14)
    )
)

fun Modifier.swiftAppBackground(): Modifier = this.background(SwiftAppGradient)

@Composable
fun SwiftGradientBackground(
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SwiftAppGradient),
        content = content
    )
}
