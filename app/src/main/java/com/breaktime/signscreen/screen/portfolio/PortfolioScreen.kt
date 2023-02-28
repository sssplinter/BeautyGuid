package com.breaktime.signscreen.screen.portfolio

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.breaktime.signscreen.screen.portfolio.SalonPortfolioContract.*
import com.breaktime.signscreen.screen.portfolio.photo.Portfolio
import com.breaktime.signscreen.ui.theme.SignScreenTheme
import com.breaktime.signscreen.uiItems.dialogs.IconButtonData
import com.breaktime.signscreen.uiItems.topBar.CommonTopAppBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch

@Composable
fun PortfolioScreen(
    salonPortfolioViewModel: SalonPortfolioViewModel = viewModel(),
    onNavigateBack: () -> Unit,
    onOpenPhoto: (Int) -> Unit
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val showLoadingDialog = remember { mutableStateOf(false) }

    initObservable(
        scope, context, salonPortfolioViewModel, onNavigateBack, onOpenPhoto, showLoadingDialog
    )

    SignScreenTheme {
        Scaffold(
            topBar = {
                CommonTopAppBar(
                    "Frau Marta",
                    modifier = Modifier,
                    navigationButton = IconButtonData(imageVector = Icons.Default.ArrowBack,
                        onClick = {
                            salonPortfolioViewModel.setEvent(SalonPortfolioEvent.OnNavigateBackClick)
                        }),
                )
            },
        ) { paddingValues ->
            Portfolio(
                Modifier.padding(paddingValues),
                onPhotoClick = { index ->
                    salonPortfolioViewModel.setEvent(
                        SalonPortfolioEvent.OnPhotoClick(index)
                    )
                })
        }
    }
}

private fun initObservable(
    composableScope: CoroutineScope,
    context: Context,
    salonPortfolioViewModel: SalonPortfolioViewModel,
    onNavigateBack: () -> Unit,
    onOpenPhoto: (Int) -> Unit,
    showLoadingDialog: MutableState<Boolean>
) {

    composableScope.launch {
        salonPortfolioViewModel.uiState.collect { state ->
            when (state) {
                is SalonPortfolioState.Loading -> {
                    showLoadingDialog.value = true
                }
                else -> {
                    showLoadingDialog.value = false
                }
            }
        }
    }

    composableScope.launch {
        salonPortfolioViewModel.effect.collect { effect ->
            composableScope.ensureActive()
            when (effect) {
                SalonPortfolioEffect.NavigateBack -> {
                    onNavigateBack()
                }
                is SalonPortfolioEffect.OpenPhoto -> {
                    onOpenPhoto(effect.photoId)
                    Toast.makeText(
                        context, effect.photoId.toString(), Toast.LENGTH_SHORT
                    ).show()
                }
                is SalonPortfolioEffect.OpenStory -> {}
                is SalonPortfolioEffect.ShowErrorMessage -> {}
                is SalonPortfolioEffect.SwitchTab -> {
                }
            }
        }
    }
}

@Preview
@Composable
fun PortfolioPreview() {
    SignScreenTheme {
        PortfolioScreen(onOpenPhoto = {}, onNavigateBack = {})
    }
}