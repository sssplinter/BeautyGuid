package com.breaktime.signscreen.screen.salonNews

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.breaktime.signscreen.data.entities.SalonNewsInfo

@Composable
fun SalonPhotosList(
    salonPhotoList: List<SalonNewsInfo>,
    onSalonClick: (Int) -> Unit,
    onSpecialistClick: (Int) -> Unit,
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
                salonNewsInfo = photoInfo,
                onSalonClick = onSalonClick,
                onSpecialistClick = onSpecialistClick
            )
        }
    }
}
