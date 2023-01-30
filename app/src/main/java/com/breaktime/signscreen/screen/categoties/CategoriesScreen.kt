package com.breaktime.signscreen.screen.categoties

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.breaktime.signscreen.screen.appointments.services.ServicesList
import com.breaktime.signscreen.ui.theme.SignScreenTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CategoriesScreen(
    viewModel: CategoriesViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState()

    val pages = mutableListOf(
        "hairs",
        "nails",
        "massage",
        "make up",
        "SPA",
        "brows",
        "epilation"
    )
    val scope = rememberCoroutineScope()

    Column(verticalArrangement = Arrangement.Top, modifier = modifier) {
        ScrollableTabRow(
            // Our selected tab is our current page
            selectedTabIndex = pagerState.currentPage,
            // Override the indicator, using the provided pagerTabIndicatorOffset modifier
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            }
        ) {
            // Add tabs for all of our pages
            pages.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.scrollToPage(index)
                        }
                    },
                )
            }
        }

        HorizontalPager(
            count = pages.size,
            state = pagerState,
        ) { page ->
            viewModel.onPageChange(page)
            ServicesList(
                viewModel.services,
                onSelectService = { service -> viewModel.selectService(service) })
        }
    }
}

@Preview
@Composable
fun PreviewCategoriesScreen() {
    SignScreenTheme {
        CategoriesScreen()
    }
}