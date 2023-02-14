package com.breaktime.signscreen.uiItems.divider

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DividerWithText(@StringRes textRes: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            color = MaterialTheme.colors.primary,
            thickness = 0.9.dp,
            modifier = Modifier.weight(0.4f)
        )
        Text(
            text = stringResource(textRes),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.weight(0.1f),
            textAlign = TextAlign.Center
        )
        Divider(
            color = MaterialTheme.colors.primary,
            thickness = 0.9.dp,
            modifier = Modifier.weight(0.4f)
        )
    }

}