package com.breaktime.signscreen.uiItems.dialogs

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.breaktime.signscreen.ui.theme.SignScreenTheme

@Composable
fun VerticalButtonsDialog(
    title: String,
    message: String,
    vararg buttons: ButtonData
) {
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.subtitle2.copy(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.SansSerif
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            text = {
                Text(
                    message
                )
            },
            buttons = {
                Column(
                    modifier = Modifier.padding(start = 32.dp, end = 32.dp, bottom = 16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    buttons.forEach { button ->
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                openDialog.value = false
                                button.onClick()
                            }
                        ) {
                            Text(button.title)
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun HorizontalButtonsDialog(
    title: String,
    message: String,
    button1Title: String,
    button2Title: String,
    onButton1Click: () -> Unit,
    onButton2Click: () -> Unit
) {
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.subtitle2.copy(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.SansSerif
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            text = {
                Text(
                    message
                )
            },
            buttons = {
                Row(
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        modifier = Modifier.size(width = 120.dp, height = 40.dp),
                        onClick = {
                            openDialog.value = false
                            onButton1Click()
                        }
                    ) {
                        Text(button1Title)
                    }
                    Button(
                        modifier = Modifier.size(width = 120.dp, height = 40.dp),
                        onClick = {
                            openDialog.value = false
                            onButton2Click()
                        }
                    ) {
                        Text(button2Title)
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun HorizontalButtonsDialogPreview() {
    SignScreenTheme {
        HorizontalButtonsDialog(
            "Dialog Title", "This area typically contains the supportive text " +
                    "which presents the details regarding the Dialog's purpose.",
            "Agree", "Dismiss", {}, {}
        )
    }
}

@Preview
@Composable
fun VerticalButtonsDialogPreview() {
    SignScreenTheme {
        VerticalButtonsDialog(
            "Dialog Title", "This area typically contains the supportive text " +
                    "which presents the details regarding the Dialog's purpose.",
            ButtonData("Agree", {}), ButtonData("Ask me later", {}),
            ButtonData("Dismiss", {})
        )
    }
}