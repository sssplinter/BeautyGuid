package com.breaktime.signscreen.uiItems.bottomDrawer

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomDrawer() {
    val (gesturesEnabled, toggleGesturesEnabled) = remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()
    Column {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .toggleable(
//                    value = gesturesEnabled,
//                    onValueChange = toggleGesturesEnabled
//                )
//        ) {
//            Checkbox(gesturesEnabled, null)
//            Text(text = if (gesturesEnabled) "Gestures Enabled" else "Gestures Disabled")
//        }
        val drawerState = rememberBottomDrawerState(BottomDrawerValue.Closed)
        BottomDrawer(
            gesturesEnabled = gesturesEnabled,
            drawerState = drawerState,
            drawerContent = {
//                Button(
//                    modifier = Modifier
//                        .align(Alignment.CenterHorizontally)
//                        .padding(top = 16.dp),
//                    onClick = { scope.launch { drawerState.close() } },
//                    content = { Text("Close Drawer") }
//                )
                LazyColumn {
                    items(25) {
                        ListItem(
                            text = { Text("Item $it") },
                            icon = {
                                Icon(
                                    Icons.Default.Favorite,
                                    contentDescription = "Localized description"
                                )
                            }
                        )
                    }
                }
            },
            content = {
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(16.dp),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    val openText = if (gesturesEnabled) "▲▲▲ Pull up ▲▲▲" else "Click the button!"
//                    Text(text = if (drawerState.isClosed) openText else "▼▼▼ Drag down ▼▼▼")
//                    Spacer(Modifier.height(20.dp))
                    Button(onClick = { scope.launch { drawerState.open() } }) {
                        Text("Click to open")
                    }
//                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewBottomDrawer() {
    BottomDrawer()
}