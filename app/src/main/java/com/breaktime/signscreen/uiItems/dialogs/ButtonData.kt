package com.breaktime.signscreen.uiItems.dialogs

import androidx.compose.ui.graphics.vector.ImageVector

data class ButtonData(
    val title: String,
    val onClick: () -> Unit
)

data class IconButtonData(
    val imageVector: ImageVector,
    val onClick: () -> Unit
)