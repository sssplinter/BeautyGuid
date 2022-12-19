package com.breaktime.signscreen.screen.appointments.services

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.breaktime.signscreen.screen.appointments.listItems.ServiceListItem
import com.breaktime.signscreen.ui.theme.SignScreenTheme


@Composable
fun ServicesList(
    modifier: Modifier = Modifier,
    serviceViewModel: ServicesViewModel = viewModel()
) {
    LazyColumn(
        modifier = modifier.background(Color.LightGray),
    ) {
        items(serviceViewModel.services) { service ->
            ServiceListItem(
                service = service.service,
                price = "${service.price} ${service.currency}",
                isSelected = service.isChecked,
                onSelect = {
                    serviceViewModel.selectService(service)
                },
                info = service.info,
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
        ServicesList()
    }
}