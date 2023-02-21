package com.breaktime.signscreen.uiItems.topBar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.breaktime.signscreen.uiItems.dialogs.IconButtonData

@Composable
fun CommonTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationButton: IconButtonData? = null,
    vararg actionButtons: IconButtonData
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                textAlign = if (navigationButton == null) TextAlign.Start else TextAlign.Center,
                style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold)
            )
        },
        navigationIcon = {
            navigationButton?.let {
                IconButton(onClick = it.onClick) {
                    Icon(it.imageVector, null)
                }
            }
        },
        backgroundColor = MaterialTheme.colors.onPrimary,
        actions = {
            actionButtons.forEach {
                IconButton(onClick = it.onClick) {
                    Icon(it.imageVector, null)
                }
            }
        }
    )
}