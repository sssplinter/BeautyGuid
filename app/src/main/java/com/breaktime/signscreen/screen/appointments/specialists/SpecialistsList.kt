package com.breaktime.signscreen.screen.appointments.specialists

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.breaktime.signscreen.screen.appointments.listItems.SpecialistListItem
import com.breaktime.signscreen.ui.theme.SignScreenTheme

@Composable
fun SpecialistsList(
    modifier: Modifier = Modifier,
    specialistsViewModel: SpecialistsViewModel = viewModel()
) {
    val context = LocalContext.current
    LazyColumn(
        modifier = modifier.background(Color.LightGray),
    ) {
        items(specialistsViewModel.specialists) { specialist ->
            SpecialistListItem(
                specialistInfo = specialist,
                onItemClick = {
                    specialistsViewModel.selectSpecialists(specialist)
                },
                onMoreInfoClick = {
                    specialistsViewModel.openMoreInfo(specialist)
                    Toast.makeText(context, "onMoreInfoClick", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .padding(horizontal = 6.dp, vertical = 3.dp)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF23434)
@Composable
fun DefaultPreview() {
    SignScreenTheme() {
        SpecialistsList()
    }
}