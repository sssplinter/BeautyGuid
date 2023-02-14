package com.breaktime.signscreen.uiItems.button

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.breaktime.signscreen.ui.theme.ButtonShape25

val colors =
    listOf(
        Color(0xFFE4759B),
        Color(0xFFff3d00),
        Color(0xFFffc107),
        Color(0xFF4caf50),
        Color(0xFF1976d2)
    )

@Composable
fun GradientBordersButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp)
            .border(
                width = 1.dp,
                brush = Brush.horizontalGradient(colors = colors),
                shape = ButtonShape25
            ),
        shape = ButtonShape25,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.onPrimary,
        ),
        content = content
    )
}