package org.jetbrains.skiko.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
internal fun Hello() {
    Box(Modifier.fillMaxSize().background(Color.LightGray)) {
        Text("Hello Compose", Modifier.align(Alignment.Center))
    }
}
