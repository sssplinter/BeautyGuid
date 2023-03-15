package com.breaktime.signscreen.uiItems.tags

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.breaktime.signscreen.ui.theme.PinkFromLogo
import com.breaktime.signscreen.ui.theme.SignScreenTheme
import com.breaktime.signscreen.ui.theme.YellowFromImage

@Composable
fun NiaTopicTag(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    onClick: () -> Unit = {},
    text: @Composable () -> Unit,
) {
    Box(modifier = modifier) {
        TextButton(
            modifier = Modifier.defaultMinSize(1.dp, 1.dp),
            shape = RoundedCornerShape(50),
            onClick = { onClick() },
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = backgroundColor.copy(alpha = 0.6f),
                contentColor = contentColorFor(backgroundColor = MaterialTheme.colors.YellowFromImage),
            )
        ) {
            ProvideTextStyle(value = MaterialTheme.typography.body2.copy(fontSize = 10.sp)) {
                text()
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    SignScreenTheme {
        NiaTopicTag(
            text = { Text("Make Up".uppercase()) },
            backgroundColor = MaterialTheme.colors.PinkFromLogo
        )
    }
}
