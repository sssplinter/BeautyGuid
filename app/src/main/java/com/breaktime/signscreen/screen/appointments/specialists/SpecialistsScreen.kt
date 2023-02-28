package com.breaktime.signscreen.screen.appointments.specialists

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.breaktime.signscreen.R
import com.breaktime.signscreen.ui.theme.BackgroundGray
import com.breaktime.signscreen.uiItems.dialogs.IconButtonData
import com.breaktime.signscreen.uiItems.topBar.CommonTopAppBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch

@Composable
fun SpecialistsScreen(
    onNavigateBack: () -> Unit,
    specialistsViewModel: SpecialistsViewModel = viewModel()
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val showLoadingDialog = remember { mutableStateOf(false) }

    initObservable(
        scope,
        context,
        specialistsViewModel,
        onNavigateBack,
        showLoadingDialog
    )

    Scaffold(
        topBar = {
            CommonTopAppBar(
                stringResource(R.string.my_masters),
                modifier = Modifier,
                navigationButton = IconButtonData(imageVector = Icons.Default.ArrowBack,
                    onClick = { specialistsViewModel.setEvent(SpecialistsContract.SpecialistsEvent.OnNavigateBackClick) }),
            )
        }, backgroundColor = MaterialTheme.colors.BackgroundGray
    ) { paddingValues ->
        SpecialistsList(
            modifier = Modifier.padding(paddingValues), specialistsViewModel,
            onMoreInfoClick = { specialistId ->
                specialistsViewModel.setEvent(
                    SpecialistsContract.SpecialistsEvent.OnSpecialistInfoClick(
                        specialistId
                    )
                )
            },
            onSalonClick = { salonId ->
                specialistsViewModel.setEvent(
                    SpecialistsContract.SpecialistsEvent.OnSalonClick(
                        salonId
                    )
                )
            },
            onBookVisitClick = { specialistId ->
                specialistsViewModel.setEvent(
                    SpecialistsContract.SpecialistsEvent.OnBookVisitClick(
                        specialistId
                    )
                )
            })
    }
}

private fun initObservable(
    composableScope: CoroutineScope,
    context: Context,
    specialistsViewModel: SpecialistsViewModel,
    onNavigateBack: () -> Unit,
    showLoadingDialog: MutableState<Boolean>
) {

    composableScope.launch {
        specialistsViewModel.uiState.collect { state ->
            when (state) {
                SpecialistsContract.SpecialistsState.Loading -> {
                    showLoadingDialog.value = true
                }
                else -> {
                    showLoadingDialog.value = false
                }

            }
        }
    }

    composableScope.launch {
        specialistsViewModel.effect.collect { effect ->
            composableScope.ensureActive()
            when (effect) {
                SpecialistsContract.SpecialistsEffect.NavigateBack -> {
                    onNavigateBack()
                }
                is SpecialistsContract.SpecialistsEffect.BookVisit -> {

                }
                is SpecialistsContract.SpecialistsEffect.OpenSalonPage -> {

                }
                is SpecialistsContract.SpecialistsEffect.OpenSpecialistInfoPage -> {

                }
                is SpecialistsContract.SpecialistsEffect.ShowErrorMessage -> {
                    Toast.makeText(
                        context, effect.errorMsg, Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
