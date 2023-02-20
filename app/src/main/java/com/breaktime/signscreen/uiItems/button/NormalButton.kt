package com.breaktime.signscreen.uiItems.button

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.breaktime.signscreen.ui.theme.ButtonShape25

@Composable
fun NormalButton(
    onClick: () -> Unit,
    @StringRes textRes: Int,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick, modifier = modifier
            .fillMaxWidth()
            .height(55.dp), shape = ButtonShape25
    ) {
        Text(
            text = stringResource(textRes),
            style = MaterialTheme.typography.subtitle1.copy(
                fontWeight = FontWeight.Bold, fontSize = 19.sp
            )
        )
    }
}