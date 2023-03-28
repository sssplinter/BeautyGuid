package com.breaktime.signscreen.screen.appointments.listItems

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.breaktime.signscreen.R
import com.breaktime.signscreen.data.entities.SpecialistInfo
import com.breaktime.signscreen.ui.theme.*
import com.breaktime.signscreen.uiItems.image.CoilImage
import com.breaktime.signscreen.uiItems.ratingBar.RatingBar
import com.breaktime.signscreen.uiItems.tags.NiaTopicTag

@Composable
fun SpecialistListItem(
    specialistInfo: SpecialistInfo,
    onItemClick: () -> Unit,
    onMoreInfoClick: () -> Unit,
    onSalonClick: () -> Unit,
    onBookVisitClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier
        .fillMaxWidth()
        .height(IntrinsicSize.Min)
        .clickable { onItemClick() }
        .border(
            2.dp,
            if (specialistInfo.isChecked) MaterialTheme.colors.BorderColor else Color.Transparent,
            RoundedCornerShape(15.dp)
        ), shape = RoundedCornerShape(15.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp, start = 10.dp, end = 10.dp, top = 14.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CoilImage(
                imageUrl = specialistInfo.photoUrl,
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
            )
            Column(Modifier.padding(start = 16.dp)) {
                Row {
                    SpecialistInformation(
                        fullName = specialistInfo.fullName,
                        specialization = specialistInfo.specialization,
                        salon = specialistInfo.salonName,
                        modifier = Modifier
                            .padding(end = 24.dp)
                            .width(200.dp),
                        onSalonClick = onSalonClick
                    )
                    IconButton(
                        onClick = { onMoreInfoClick() }, modifier = Modifier.size(35.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_info), contentDescription = null
                        )
                    }
                }
                SpecialistRating(
                    specialistInfo.rating,
                    specialistInfo.marksCount,
                    onBookVisitClick = onBookVisitClick
                )
            }

        }
    }
}

@Composable
fun SpecialistInformation(
    fullName: String,
    specialization: String,
    modifier: Modifier = Modifier,
    onSalonClick: () -> Unit,
    salon: String? = null
) {
    Column(modifier = modifier) {
        Text(
            text = specialization, style = MaterialTheme.typography.caption
        )
        Text(
            text = fullName, style = MaterialTheme.typography.h6.copy(fontSize = 18.sp)
        )
        salon?.let {
            NiaTopicTag(
                modifier = Modifier.padding(vertical = 4.dp),
                text = "Salon: ${salon.uppercase()}",
                textStyle =MaterialTheme.typography.body1.copy(
                    textDecoration = TextDecoration.Underline, fontSize = 12.sp, fontWeight = FontWeight.W600
                ),
                backgroundColor = MaterialTheme.colors.PinkFromLogo.copy(alpha = 0.4f),
                onClick = onSalonClick
            )
        }
    }
}

@Composable
fun SpecialistRating(
    rating: Double,
    marksCount: Int,
    modifier: Modifier = Modifier,
    onBookVisitClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth(),
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

        Button(
            onClick = { onBookVisitClick() },
            modifier = Modifier
                .padding(end = 8.dp)
                .height(35.dp),
            shape = RoundedCornerShape(50)
        ) {
            Row {
                Text(
                    text = stringResource(R.string.book_visit),
                    style = MaterialTheme.typography.caption.copy(fontSize = 13.sp)
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null
                )
            }
        }
    }
}