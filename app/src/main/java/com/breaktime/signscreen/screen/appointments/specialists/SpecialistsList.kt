package com.breaktime.signscreen.screen.appointments.specialists

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.breaktime.signscreen.screen.appointments.listItems.SpecialistListItem
import com.breaktime.signscreen.ui.theme.BackgroundGray

@Composable
fun SpecialistsList(
    modifier: Modifier = Modifier,
    specialistsViewModel: SpecialistsViewModel,
    onMoreInfoClick: (String) -> Unit,
    onSalonClick: (String) -> Unit,
    onBookVisitClick: (String) -> Unit,
) {
    LazyColumn(
        modifier = modifier.background(MaterialTheme.colors.BackgroundGray),
    ) {
        items(specialistsViewModel.specialists) { specialist ->
            SpecialistListItem(
                specialistInfo = specialist,
                onItemClick = {
                    specialistsViewModel.selectSpecialists(specialist)
                },
                onMoreInfoClick = {
//                    onMoreInfoClick(specialist.specialistId)
                },
                onSalonClick = {
//                    onSalonClick(specialist.salonId)
                },
                onBookVisitClick = {
//                    onBookVisitClick(specialist.specialistId)
                },
                modifier = Modifier
                    .padding(horizontal = 6.dp, vertical = 3.dp)
            )
        }
    }
}