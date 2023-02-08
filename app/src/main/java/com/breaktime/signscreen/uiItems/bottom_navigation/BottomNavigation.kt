package com.breaktime.signscreen.uiItems.bottom_navigation

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.breaktime.signscreen.R

@Composable
private fun SootheBottomNavigation(modifier: Modifier = Modifier) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background, modifier = modifier
    ) {
        BottomNavigationItem(icon = {
            Icon(
                imageVector = Icons.Default.Add, contentDescription = null
            )
        }, label = {
            Text(stringResource(id = R.string.bottom_navigation_home))
        }, selected = true, onClick = {})
        BottomNavigationItem(icon = {
            Icon(
                imageVector = Icons.Default.AccountCircle, contentDescription = null
            )
        }, label = {
            Text(stringResource(id = R.string.bottom_navigation_profile))
        }, selected = false, onClick = {})
    }
}

// TODO to call
//SignScreenTheme {
//    Scaffold { paddingValues ->
//        Portfolio(Modifier.padding(paddingValues))
//    }
//}
