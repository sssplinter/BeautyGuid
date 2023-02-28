package com.breaktime.signscreen.screen.photoPreview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.breaktime.signscreen.R
import com.breaktime.signscreen.data.entities.SalonPhotoInfo
import com.breaktime.signscreen.ui.theme.PinkFromLogo
import com.breaktime.signscreen.ui.theme.SignScreenTheme

@Composable
fun SalonPhotoItem(
    modifier: Modifier = Modifier,
    salonPhotoInfo: SalonPhotoInfo,
    salonName: String,
    salonPhoto: Int
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 18.dp)
    ) {
        HeaderSection(salonName, salonPhoto)
        PhotoSection(salonPhotoInfo.photo)
        InfoSection(salonPhotoInfo.masterName, salonPhotoInfo.description)
    }
}

@Composable
fun HeaderSection(salonName: String, salonPhoto: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(vertical = 10.dp, horizontal = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(salonPhoto),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                text = salonName, style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.Medium,
                )
            )
            Text(text = stringResource(R.string.salon), style = MaterialTheme.typography.caption)
        }
    }
}

@Composable
fun PhotoSection(imageId: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = imageId),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    )
}

@Composable
fun InfoSection(masterName: String, photoDescription: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(vertical = 10.dp, horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Row {
            Text(
                text = stringResource(R.string.master),
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            )
            Row(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .height(20.dp)
                    .clickable { }
                    .background(
                        MaterialTheme.colors.PinkFromLogo.copy(
                            alpha = 0.7f
                        ), RoundedCornerShape(50)

                    ),
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                    text = masterName,
                    style = MaterialTheme.typography.body1.copy(
                        textDecoration = TextDecoration.Underline, fontSize = 13.sp
                    )
                )
            }
        }
        Text(
            modifier = Modifier.padding(top = 6.dp),
            text = photoDescription,
            style = MaterialTheme.typography.caption.copy(fontSize = 13.sp)
        )

    }
}

@Preview
@Composable
fun SalonPhotoPreview() {
    SignScreenTheme {
        Column {
            SalonPhotoItem(
                salonPhotoInfo = SalonPhotoInfo(
                    0,
                    R.drawable.im_nails,
                    "Kristina Sementsova",
                    "2",
                    stringResource(id = R.string.test_photo_description)
                ),
                salonName = "Frau Marta",
                salonPhoto = R.drawable.fc5_overwhelmed,
            )
        }
    }
}