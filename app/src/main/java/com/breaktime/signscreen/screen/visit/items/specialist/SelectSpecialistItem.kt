package com.breaktime.signscreen.screen.visit.items.specialist

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.People
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.breaktime.signscreen.R
import com.breaktime.signscreen.screen.visit.items.DefaultUnselectedChooseSection
import com.breaktime.signscreen.screen.visit.items.SlotSection
import com.breaktime.signscreen.ui.theme.BorderColor
import com.breaktime.signscreen.ui.theme.hintColor
import com.breaktime.signscreen.uiItems.image.CoilImage
import com.breaktime.signscreen.uiItems.ratingBar.RatingBar

@Composable
fun SelectSpecialistSection(
    modifier: Modifier = Modifier,
    specialistInfo: SelectedSpecialistInfo?,
    onClick: () -> Unit,
    onDeselect: () -> Unit
) {
    SlotSection(modifier = modifier,
        onClick = { onClick() },
        isSelected = specialistInfo != null,
        defaultContent = {
            DefaultUnselectedChooseSection(
                stringResource(R.string.choose_specialist),
                Icons.Default.People
            )
        },
        selectedContent = {
            specialistInfo?.let {
                SelectedSpecialistItem(
                    selectedSpecialistInfo = specialistInfo,
                    onItemClick = { /*TODO*/ },
                    onDeselectClick = { onDeselect() },
                )
            }
        })
}

@Composable
fun SelectedSpecialistItem(
    selectedSpecialistInfo: SelectedSpecialistInfo,
    onItemClick: () -> Unit,
    onDeselectClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(modifier = modifier
        .fillMaxWidth()
        .height(IntrinsicSize.Min)
        .clickable { onItemClick() }
        .border(
            2.dp,
            if (selectedSpecialistInfo.isChecked) MaterialTheme.colors.BorderColor else Color.Transparent,
            RoundedCornerShape(15.dp)
        ), shape = RoundedCornerShape(15.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp, start = 10.dp, end = 16.dp, top = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CoilImage(
                imageUrl = selectedSpecialistInfo.photoUrl,
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
            )
            Column(Modifier.padding(start = 16.dp)) {
                SpecialistInformation(
                    fullName = selectedSpecialistInfo.fullName,
                    specialisation = selectedSpecialistInfo.specialisation,
                    modifier = Modifier
                        .width(180.dp)
                )

                BottomSection(
                    selectedSpecialistInfo.rating,
                    selectedSpecialistInfo.marksCount,
                )
            }
            IconButton(
                onClick = { onDeselectClick() }, modifier = Modifier.size(35.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun SpecialistInformation(
    fullName: String,
    specialisation: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = specialisation, style = MaterialTheme.typography.caption
        )
        Text(
            text = fullName, style = MaterialTheme.typography.h6.copy(fontSize = 18.sp)
        )
    }
}

@Composable
fun BottomSection(
    rating: Double,
    marksCount: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Row {
            RatingBar(modifier = Modifier.height(17.dp), rating = rating)
            Text(
                text = marksCount.toString(),
                style = MaterialTheme.typography.caption.copy(fontSize = 12.sp),
                color = MaterialTheme.colors.hintColor,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}