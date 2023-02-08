package com.breaktime.signscreen.screen.portfolio

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.breaktime.signscreen.screen.portfolio.photo.Portfolio
import com.breaktime.signscreen.ui.theme.SignScreenTheme
import com.breaktime.signscreen.ui.theme.salonH6

@Composable
fun PortfolioScreen() {
    SignScreenTheme {
        Scaffold(topBar = { TopAppBarSample() }) { paddingValues ->
            Portfolio(Modifier.padding(paddingValues))
        }
    }
}

@Composable
fun TopAppBarSample(modifier: Modifier = Modifier) {
    TopAppBar(
        modifier = modifier.height(45.dp),
        elevation = 4.dp,
        title = {
            Text("Frau Marta Salon", style = MaterialTheme.typography.salonH6)
        },
        backgroundColor = MaterialTheme.colors.onPrimary,
        navigationIcon = {
            IconButton(onClick = {/* Do Something*/ }) {
                Icon(Icons.Filled.ArrowBack, null)
            }
        }, actions = {
            IconButton(onClick = {/* Do Something*/ }) {
                Icon(Icons.Filled.QuestionAnswer, null)
            }
            IconButton(onClick = {/* Do Something*/ }) {
                Icon(Icons.Filled.LocationOn, null)
            }
        })
}


@Preview
@Composable
fun PortfolioPreview() {
    SignScreenTheme {
        PortfolioScreen()
    }
}