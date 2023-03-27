package com.breaktime.signscreen.uiItems.tags

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NiaTopicTag(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    backgroundColor: Color,
    onClick: () -> Unit = {},
    text: String,
) {
    Box(modifier = modifier.clickable { onClick() }) {
        Surface(
            modifier = Modifier.border(
                1.dp,
                if (isSelected) Color.Black else Color.Transparent,
                RoundedCornerShape(50)
            ),
            shape = RoundedCornerShape(50), color = backgroundColor.copy(alpha = 0.6f)
        ) {
            Text(
                text = text.uppercase(),
                modifier = Modifier
                    .padding(vertical = 3.dp, horizontal = 5.dp),
                style = MaterialTheme.typography.body2.copy(
                    fontSize = if (isSelected) 11.sp else 10.sp, fontWeight = FontWeight.W600
                )
            )
        }
    }
}
