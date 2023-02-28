package com.breaktime.signscreen.screen.appointments.listItems

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.breaktime.signscreen.R
import com.breaktime.signscreen.data.entities.SpecialistInfo
import com.breaktime.signscreen.ui.theme.BorderColor
import com.breaktime.signscreen.ui.theme.PinkFromLogo
import com.breaktime.signscreen.ui.theme.hintColor
import com.breaktime.signscreen.uiItems.ratingBar.RatingBar

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
                .padding(all = 16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ListItemRoundImage(
                imageId = specialistInfo.imageId, modifier = Modifier.size(70.dp)
            )
            Column(Modifier.padding(start = 16.dp)) {
                Row {
                    SpecialistInformation(
                        fullName = specialistInfo.fullName,
                        specialization = specialistInfo.specialization,
                        salon = "Marcel",
                        modifier = Modifier
                            .padding(end = 24.dp)
                            .width(200.dp),
                        onSalonClick = onSalonClick
                    )
                    IconButton(
                        onClick = { onMoreInfoClick() }, modifier = Modifier.size(40.dp)
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

// TODO replace drawable by real image
@Composable
fun ListItemRoundImage(imageId: Int, modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.clip(CircleShape),
        painter = painterResource(imageId),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
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
        salon?.let {
            Row(
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .height(20.dp)
                    .clickable { onSalonClick() }
                    .background(
                        MaterialTheme.colors.PinkFromLogo.copy(
                            alpha = 0.7f
                        ), RoundedCornerShape(50)

                    ),
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                    text = salon,
                    style = MaterialTheme.typography.body1.copy(
                        textDecoration = TextDecoration.Underline, fontSize = 12.sp
                    )
                )
            }
        }
        Text(
            text = specialization, style = MaterialTheme.typography.caption
        )
        Text(
            text = fullName, style = MaterialTheme.typography.h6
        )
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
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            RatingBar(modifier = Modifier.height(17.dp), rating = rating)
            Text(
                text = marksCount.toString(),
                style = MaterialTheme.typography.caption.copy(fontSize = 13.sp),
                color = MaterialTheme.colors.hintColor,
                modifier = Modifier.padding(start = 4.dp)
            )
        }

        Button(
            onClick = { onBookVisitClick() },
            modifier = Modifier
                .padding(top = 20.dp)
                .height(30.dp),
            shape = RoundedCornerShape(50)
        ) {
            Row {
                Text(
                    text = "Book a visit",
                    style = MaterialTheme.typography.caption.copy(fontSize = 12.sp)
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null
                )
            }
        }
    }
}