package com.breaktime.signscreen.screen.appointments.services

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.breaktime.signscreen.data.entities.ServiceInfo
import com.breaktime.signscreen.screen.appointments.listItems.ServiceListItem


@Composable
fun ServicesList(
    services: List<ServiceInfo>,
    onSelectService: (ServiceInfo) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.background(Color.LightGray),
    ) {
        items(services) { service ->
            ServiceListItem(
                service = service.service,
                price = "${service.price} ${service.currency}",
                isSelected = service.isChecked,
                onSelect = {
                    onSelectService(service)
                },
                info = service.info,
                modifier = Modifier
                    .padding(horizontal = 6.dp, vertical = 3.dp)
            )
        }
    }
}