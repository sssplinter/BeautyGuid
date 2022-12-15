package com.breaktime.signscreen.screen.appointments.listItems

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.breaktime.signscreen.R
import com.breaktime.signscreen.data.entities.SpecialistInfo
import com.breaktime.signscreen.ui.theme.SignScreenTheme
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
            .clickable { onItemClick() },
        shape = RoundedCornerShape(15.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.White, CircleShape),
                painter = painterResource(specialistInfo.imageId),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.width(220.dp)) {
                Text(
                    text = specialistInfo.specialization,
                    style = MaterialTheme.typography.caption
                )
                Text(
                    text = specialistInfo.fullName,
                    style = MaterialTheme.typography.h6
                )
                SpecialistRating(specialistInfo.rating, specialistInfo.marksCount)
            }

            IconButton(onClick = { onMoreInfoClick() }, modifier = Modifier.size(40.dp)) {
                Icon(
                    painter = painterResource(R.drawable.ic_info),
                    contentDescription = "Информация о приложении", modifier = Modifier.size(80.dp)
                )
            }
        }
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

@Preview(showBackground = true, backgroundColor = 0xFFF23434)
@Composable
fun PreviewSpecialistListItem() {
    SignScreenTheme {
        val specialistInfo = SpecialistInfo(
            "11",
            "Kristina Sementsova",
            "Eyebrow stylist, makeup artist",
            R.drawable.ab1_inversions,
            3.3,
            127
        )
        val context = LocalContext.current
        SpecialistListItem(
            specialistInfo,
            { Toast.makeText(context, "onItemClick", Toast.LENGTH_SHORT).show() },
            { Toast.makeText(context, "onMoreInfoClick", Toast.LENGTH_SHORT).show() })
    }
}