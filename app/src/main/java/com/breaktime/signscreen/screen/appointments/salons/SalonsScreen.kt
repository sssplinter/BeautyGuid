package com.breaktime.signscreen.screen.appointments.salons

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.breaktime.signscreen.R
import com.breaktime.signscreen.appComponent
import com.breaktime.signscreen.ui.theme.BackgroundGray
import com.breaktime.signscreen.ui.theme.SignScreenTheme
import com.breaktime.signscreen.uiItems.dialogs.IconButtonData
import com.breaktime.signscreen.uiItems.serch_bar.SearchBar
import com.breaktime.signscreen.uiItems.tags.NiaTopicTag
import com.breaktime.signscreen.uiItems.topBar.CommonTopAppBar
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch

@Composable
fun SalonsScreen(
    onNavigateBack: () -> Unit,
    onOpenSalonPortfolio: (Int) -> Unit,
    salonsListViewModel: SalonsListViewModel = viewModel(factory = LocalContext.current.appComponent.salonsListViewModel()),
) {
    val showSearchBar = remember { mutableStateOf(false) }
    val showFiltersList = remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val showLoadingDialog = remember { mutableStateOf(false) }

    initObservable(
        scope, salonsListViewModel, onNavigateBack, onOpenSalonPortfolio, showLoadingDialog
    )

    Scaffold(
        topBar = {
            CommonTopAppBar(
                stringResource(R.string.my_salons),
                modifier = Modifier,
                navigationButton = IconButtonData(imageVector = Icons.Default.ArrowBack,
                    onClick = { salonsListViewModel.setEvent(SalonsListContract.SalonsListEvent.OnNavigateBackClick) }),
                IconButtonData(imageVector = Icons.Default.Search, onClick = {
                    showSearchBar.value = !showSearchBar.value
                }),
                IconButtonData(imageVector = Icons.Default.FilterAlt, onClick = {
                    showFiltersList.value = !showFiltersList.value
                })
            )
        }, backgroundColor = MaterialTheme.colors.BackgroundGray
    ) { paddingValues ->
        Column {
            if (showSearchBar.value) {
                SearchBar(value = salonsListViewModel.searchValue,
                    onValueChange = { value -> salonsListViewModel.onSearchValueChange(value) },
                    onClear = { salonsListViewModel.onSearchValueChange() })
            }
            if (showFiltersList.value) {
                FilterList(
                    selectedCategory = salonsListViewModel.selectedCategory,
                    onCategoryClick = { category -> salonsListViewModel.setCategory(category) })
            }
            SalonsList(modifier = Modifier.padding(paddingValues),
                salonsListViewModel,
                onSalonClick = { salonId ->
                    salonsListViewModel.setEvent(
                        SalonsListContract.SalonsListEvent.OnOpenSalonPortfolio(
                            salonId
                        )
                    )
                })
        }
    }
}

@Composable
fun FilterList(
    selectedCategory: String,
    onCategoryClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val categories = CategoryEnum.values()
    Column(modifier = modifier.padding(horizontal = 12.dp, vertical = 6.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Select category", style = MaterialTheme.typography.h6)
            IconButton(
                modifier = Modifier.size(20.dp),
                onClick = { onCategoryClick("") }) {
                Icon(imageVector = Icons.Default.Close, contentDescription = null)
            }
        }
        FlowRow(
            modifier = Modifier.padding(top = 6.dp),
            mainAxisSpacing = 3.dp,
            crossAxisSpacing = 3.dp
        ) {
            for (index in categories) {
                NiaTopicTag(
                    isSelected = index.name == selectedCategory,
                    text = index.title,
                    backgroundColor = index.color,
                    onClick = { onCategoryClick(index.name) }
                )
            }
        }
    }
}

private fun initObservable(
    composableScope: CoroutineScope,
    salonsListViewModel: SalonsListViewModel,
    onNavigateBack: () -> Unit,
    onOpenSalonPortfolio: (Int) -> Unit,
    showLoadingDialog: MutableState<Boolean>
) {

    composableScope.launch {
        salonsListViewModel.uiState.collect { state ->
            when (state) {
                SalonsListContract.SalonsListState.Loading -> {
                    showLoadingDialog.value = true
                }
                else -> {
                    showLoadingDialog.value = false
                }
            }
        }
    }

    composableScope.launch {
        salonsListViewModel.effect.collect { effect ->
            composableScope.ensureActive()
            when (effect) {
                SalonsListContract.SalonsListEffect.NavigateBack -> {
                    onNavigateBack()
                }
                is SalonsListContract.SalonsListEffect.OpenSalonPortfolio -> {
                    onOpenSalonPortfolio(effect.salonId)
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewSalonsScreen() {
    SignScreenTheme {
        SalonsScreen(onNavigateBack = { }, onOpenSalonPortfolio = {})
    }
}