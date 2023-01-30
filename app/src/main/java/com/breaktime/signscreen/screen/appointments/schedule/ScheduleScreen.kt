package com.breaktime.signscreen.screen.appointments.schedule

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.breaktime.signscreen.ui.theme.SignScreenTheme
import com.breaktime.signscreen.ui.theme.unreachable
import io.github.boguszpawlowski.composecalendar.CalendarState
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.rememberSelectableCalendarState
import io.github.boguszpawlowski.composecalendar.selection.DynamicSelectionState
import io.github.boguszpawlowski.composecalendar.selection.SelectionMode
import java.time.DayOfWeek
import java.time.LocalDate


@Composable
fun SelectableCalendarSample() {
    val calendarState = rememberSelectableCalendarState()

//    VisitDateSelectionCalendar(calendarState)
    SeeVisitsCalendar(calendarState)
}

@Composable
fun VisitDateSelectionCalendar(calendarState: CalendarState<DynamicSelectionState>) {
    calendarState.selectionState.selectionMode = SelectionMode.Single

    val currentDate = LocalDate.now()

    Column(
        Modifier.verticalScroll(rememberScrollState())
    ) {
        SelectableCalendar(calendarState = calendarState,
            firstDayOfWeek = DayOfWeek.MONDAY,
            dayContent = {
                DayContent(
                    state = it,
                    currentDate = currentDate,
                    defaultTextColor = MaterialTheme.colors.primary,
                    selectionTextColor = MaterialTheme.colors.onPrimary,
                    defaultBackgroundColor = MaterialTheme.colors.surface,
                    selectionBackgroundColor = MaterialTheme.colors.primary,
                    unreachableDayTextColor = MaterialTheme.colors.unreachable
                )
            })
    }
}

@Composable
fun SeeVisitsCalendar(calendarState: CalendarState<DynamicSelectionState>) {
    calendarState.selectionState.selectionMode = SelectionMode.Multiple

    val currentDate = LocalDate.now()

    Column(
        Modifier.verticalScroll(rememberScrollState())
    ) {
        SelectableCalendar(calendarState = calendarState,
            firstDayOfWeek = DayOfWeek.MONDAY,
            dayContent = {
                DayContent(
                    state = it,
                    currentDate = currentDate,
                    defaultTextColor = MaterialTheme.colors.primary,
                    selectionTextColor = MaterialTheme.colors.onPrimary,
                    defaultBackgroundColor = MaterialTheme.colors.surface,
                    selectionBackgroundColor = MaterialTheme.colors.primary,
                    unreachableDayTextColor = MaterialTheme.colors.unreachable
                )
            })
    }
}

@Composable
private fun DayContent(
    state: DayState<DynamicSelectionState>,
    currentDate: LocalDate,
    modifier: Modifier = Modifier,
    defaultTextColor: Color = MaterialTheme.colors.primary,
    selectionTextColor: Color = MaterialTheme.colors.primary,
    defaultBackgroundColor: Color = MaterialTheme.colors.surface,
    selectionBackgroundColor: Color = MaterialTheme.colors.primary,
    unreachableDayTextColor: Color = MaterialTheme.colors.unreachable,
    currentDayColor: Color = MaterialTheme.colors.primary,
    onClick: (LocalDate) -> Unit = {},
) {
    val date = state.date
    val selectionState = state.selectionState

    val isSelected = selectionState.isDateSelected(date)
    val isUnreachable = currentDate.isAfter(date)

    Card(
        modifier = modifier
            .aspectRatio(1f)
            .padding(2.dp),
        shape = RoundedCornerShape(20),
        backgroundColor = if (isSelected) selectionBackgroundColor else defaultBackgroundColor,
        elevation = if (state.isFromCurrentMonth) 4.dp else 0.dp,
        border = if (state.isCurrentDay) BorderStroke(1.dp, currentDayColor) else null,
        contentColor = if (isUnreachable) {
            unreachableDayTextColor
        } else {
            if (isSelected) selectionTextColor else defaultTextColor
        }
    ) {
        Box(
            modifier = Modifier
                .clickable {
                    if (!isUnreachable) {
                        onClick(date)
                        selectionState.onDateSelected(date)
                    }
                },
            contentAlignment = Alignment.Center,
        ) {
            Text(text = date.dayOfMonth.toString())
        }
    }
}

@Composable
@Preview
fun SchedulePreview() {
    SignScreenTheme {
        SelectableCalendarSample()
    }
}