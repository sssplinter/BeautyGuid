package com.breaktime.signscreen.screen.appointments.listItems

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.breaktime.signscreen.R
import com.breaktime.signscreen.data.entities.SpecialistInfo
import com.breaktime.signscreen.ui.theme.BorderColor
import com.breaktime.signscreen.ui.theme.HintColor
import com.breaktime.signscreen.uiItems.ratingBar.RatingBar

@Composable
fun SpecialistListItem(
    specialistInfo: SpecialistInfo,
    onItemClick: () -> Unit,
    onMoreInfoClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .clickable { onItemClick() }
            .border(
                2.dp,
                if (specialistInfo.isChecked) MaterialTheme.colors.BorderColor else Color.Transparent,
                RoundedCornerShape(15.dp)
            ),
        shape = RoundedCornerShape(15.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ListItemRoundImage(
                imageId = specialistInfo.imageId,
                modifier = Modifier.size(80.dp)
            )
            SpecialistInformation(
                fullName = specialistInfo.fullName,
                specialization = specialistInfo.specialization,
                rating = specialistInfo.rating,
                marksCount = specialistInfo.marksCount,
                modifier = Modifier.width(200.dp)
            )
            IconButton(
                onClick = { onMoreInfoClick() },
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_info),
                    contentDescription = null
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
    rating: Double,
    marksCount: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = specialization,
            style = MaterialTheme.typography.caption
        )
        Text(
            text = fullName,
            style = MaterialTheme.typography.h6
        )
        SpecialistRating(rating, marksCount)
    }
}

@Composable
fun SpecialistRating(rating: Double, marksCount: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .width(150.dp)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        RatingBar(rating = rating)
        Text(text = marksCount.toString(), color = MaterialTheme.colors.HintColor)
    }
}