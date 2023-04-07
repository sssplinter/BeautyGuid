package com.breaktime.signscreen.screen.visit.items.service

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.breaktime.signscreen.R
import com.breaktime.signscreen.screen.visit.items.DefaultUnselectedChooseSection
import com.breaktime.signscreen.ui.theme.NotPrimaryText


@Composable
fun SelectServiceSection(
    selectedServices: List<SelectedService>,
    modifier: Modifier = Modifier,
    onSelectClick: () -> Unit,
    onDeselectService: (Int) -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(18.dp)
    ) {
        Column {
            DefaultUnselectedChooseSection(
                modifier = Modifier.clickable { onSelectClick() },
                title = stringResource(R.string.choose_service),
                icon = Icons.Default.Checklist
            )

            for (service in selectedServices) {
                SelectedServiceItem(
                    selectedService = service,
                    onDeselect = { onDeselectService(service.id) })
            }
        }
    }
}

@Composable
fun SelectedServiceItem(
    selectedService: SelectedService,
    onDeselect: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 16.dp, top = 10.dp, bottom = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = selectedService.price.toString() + " BYN",
                    style = MaterialTheme.typography.h5.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )
                Text(
                    modifier = Modifier.padding(start = 32.dp),
                    color = MaterialTheme.colors.NotPrimaryText,
                    text = selectedService.duration.toInt().toString() + " min.",
                    style = MaterialTheme.typography.caption.copy(fontSize = 14.sp)
                )
            }
            Text(
                text = selectedService.name,
                style = MaterialTheme.typography.caption.copy(fontSize = 15.sp)
            )
        }

        IconButton(
            onClick = { onDeselect() }, modifier = Modifier.size(35.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null
            )
        }
    }
}