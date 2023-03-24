package com.breaktime.signscreen.screen.appointments.salons

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.breaktime.signscreen.R
import com.breaktime.signscreen.data.entities.SalonInfo
import com.breaktime.signscreen.screen.salonList.SalonListItem
import com.breaktime.signscreen.ui.theme.BackgroundGray

@Composable
fun SalonsList(
    modifier: Modifier = Modifier,
    salonsListViewModel: SalonsListViewModel,
    onSalonClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier.background(MaterialTheme.colors.BackgroundGray),
    ) {
        items(salonsListViewModel.salons) { salonPreview ->
            SalonListItem(
                salonInfo = SalonInfo(
                    salonId = salonPreview.salonId,
                    salonName = salonPreview.salonName,
                    salonDescription = salonPreview.salonDescription,
                    imageId = R.drawable.im_nails3,
                    categories = salonPreview.categories
                ),
                onSalonClick = {
                    onSalonClick(salonPreview.salonId)
                })
        }
    }
}