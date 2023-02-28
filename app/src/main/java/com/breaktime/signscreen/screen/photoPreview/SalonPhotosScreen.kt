package com.breaktime.signscreen.screen.photoPreview

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.breaktime.signscreen.R
import com.breaktime.signscreen.ui.theme.SignScreenTheme
import com.breaktime.signscreen.uiItems.dialogs.IconButtonData
import com.breaktime.signscreen.uiItems.topBar.CommonTopAppBar

@Composable
fun SalonPhotosScreen(
    salonPhotosViewModel: SalonPhotosViewModel = viewModel(),
    onNavigateBack: () -> Unit,
    salonId: String,
    scrollToIndex: Int = 0
) {
    Scaffold(
        topBar = {
            CommonTopAppBar(
                stringResource(R.string.publications),
                modifier = Modifier,
                navigationButton = IconButtonData(imageVector = Icons.Default.ArrowBack,
                    onClick = {
                        onNavigateBack()
                    })
            )
        }
    )
    { paddingValues ->
        SalonPhotosList(
            modifier = Modifier.padding((paddingValues)),
            salonPhotoList = salonPhotosViewModel.salonPhotosInfo,
            salonName = salonPhotosViewModel.salonName,
            salonPhoto = salonPhotosViewModel.salonPhoto,
            scrollToIndex = scrollToIndex
        )
    }
}

@Preview
@Composable
fun SalonPhotosScreenPreview() {
    SignScreenTheme {
        SalonPhotosScreen(onNavigateBack = {}, salonId = "Frau Marta")
    }
}