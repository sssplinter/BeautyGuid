package com.breaktime.signscreen.screen.salonNews

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.breaktime.signscreen.R
import com.breaktime.signscreen.data.entities.SalonNewsInfo
import com.breaktime.signscreen.ui.theme.PinkFromLogo
import com.breaktime.signscreen.uiItems.image.CoilImage
import com.breaktime.signscreen.uiItems.tags.NiaTopicTag

@Composable
fun SalonPhotoItem(
    salonNewsInfo: SalonNewsInfo,
    onSalonClick: (Int) -> Unit,
    onSpecialistClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 18.dp)
    ) {
        HeaderSection(
            salonName = salonNewsInfo.salonName,
            salonDescription = salonNewsInfo.salonDescription,
            salonPhotoUrl = salonNewsInfo.salonPhotoUrl,
            modifier = Modifier.clickable { onSalonClick(salonNewsInfo.salonId) }
        )
        PhotoSection(salonNewsInfo.newsPhotoUrl)
        InfoSection(
            salonNewsInfo.specialistName,
            salonNewsInfo.newsDescription,
            onSpecialistClick = {
                onSpecialistClick(salonNewsInfo.specialistId)
            })
    }
}

@Composable
fun HeaderSection(
    salonName: String,
    salonDescription: String,
    salonPhotoUrl: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(vertical = 10.dp, horizontal = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CoilImage(
            imageUrl = salonPhotoUrl,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                text = salonName, style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.Medium,
                )
            )
            Text(text = salonDescription, style = MaterialTheme.typography.caption)
        }
    }
}

@Composable
fun PhotoSection(newsPhotoUrl: String, modifier: Modifier = Modifier) {
    CoilImage(
        imageUrl = newsPhotoUrl, modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    )
}

@Composable
fun InfoSection(
    masterName: String, photoDescription: String,
    onSpecialistClick: () -> Unit, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(vertical = 10.dp, horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Row {
            Text(
                text = stringResource(R.string.specialist),
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                ),
                modifier = Modifier.padding(vertical = 2.dp)
            )

            NiaTopicTag(
                modifier = Modifier.padding(horizontal = 4.dp),
                text = masterName,
                textStyle = MaterialTheme.typography.body1.copy(
                    textDecoration = TextDecoration.Underline,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.W600
                ),
                backgroundColor = MaterialTheme.colors.PinkFromLogo.copy(alpha = 0.4f),
                onClick = onSpecialistClick
            )
        }
        Text(
            modifier = Modifier.padding(top = 6.dp),
            text = photoDescription,
            style = MaterialTheme.typography.caption.copy(fontSize = 14.sp)
        )

    }
}