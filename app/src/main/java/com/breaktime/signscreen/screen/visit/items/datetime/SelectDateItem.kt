package com.breaktime.signscreen.screen.visit.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.breaktime.signscreen.R
import com.breaktime.signscreen.screen.visit.items.datetime.SelectedDateTimeInfo
import com.breaktime.signscreen.ui.theme.unreachable

@Composable
fun SelectDateTimeSection(
    selectedDateTimeInfo: SelectedDateTimeInfo?,
    onClick: () -> Unit,
    onDeselect: () -> Unit,
    modifier: Modifier = Modifier
) {
    SlotSection(modifier = modifier,
        onClick = { onClick() },
        isSelected = selectedDateTimeInfo != null,
        defaultContent = {
            DefaultUnselectedChooseSection(
                stringResource(R.string.choose_date_time),
                Icons.Default.CalendarMonth
            )
        },
        selectedContent = {
            selectedDateTimeInfo?.let {
                DateTimeSection(selectedDateTimeInfo = selectedDateTimeInfo, onDeselect = onDeselect)
            }
        })
}

@Composable
fun DateTimeSection(
    selectedDateTimeInfo: SelectedDateTimeInfo,
    onDeselect: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 16.dp, top = 16.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            Icon(
                imageVector = Icons.Default.CalendarMonth,
                contentDescription = null,
                modifier = Modifier
                    .background(MaterialTheme.colors.unreachable, CircleShape)
                    .padding(8.dp)
            )
            Column {
                Text(
                    text = selectedDateTimeInfo.date,
                    Modifier.padding(start = 32.dp),
                    style = MaterialTheme.typography.caption.copy(fontSize = 15.sp)
                )
                Text(
                    text = selectedDateTimeInfo.time,
                    Modifier.padding(start = 32.dp),
                    style = MaterialTheme.typography.h5.copy(fontSize = 17.sp)
                )
            }
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