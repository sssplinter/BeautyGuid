package com.breaktime.signscreen.screen.photoPreview

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.breaktime.signscreen.R
import com.breaktime.signscreen.data.entities.SalonPhotoInfo
import com.breaktime.signscreen.ui.theme.SignScreenTheme

@Composable
fun SalonPhotosList(
    salonName: String,
    salonPhoto: Int,
    salonPhotoList: List<SalonPhotoInfo>,
    modifier: Modifier = Modifier,
    scrollToIndex: Int = 0
) {
    val listState = rememberLazyListState(scrollToIndex)

    LazyColumn(
        state = listState,
        modifier = modifier
    ) {
        items(salonPhotoList) { photoInfo ->
            SalonPhotoItem(
                salonPhotoInfo = photoInfo,
                salonName = salonName,
                salonPhoto = salonPhoto
            )
        }
    }
}

@Preview
@Composable
fun PhotoPreviewListPreview() {
    SignScreenTheme {
        SalonPhotosList(
            salonName = "Frau Marta",
            salonPhoto = R.drawable.fc5_overwhelmed,
            salonPhotoList = listOf()
        )
    }
}