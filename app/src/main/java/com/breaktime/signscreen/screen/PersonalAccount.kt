package com.breaktime.signscreen.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.breaktime.signscreen.R
import com.breaktime.signscreen.data.entities.SpecialistInfo
import com.breaktime.signscreen.ui.theme.SignScreenTheme

@Composable
fun PersonalAccountScreen(
    specialistInfo: SpecialistInfo,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth().height(100.dp),
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            modifier = Modifier.width(192.dp).padding(all = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(specialistInfo.imageId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(56.dp)
            )
            Text(
                text = specialistInfo.fullName,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun PreviewSpecialistListItem() {
    SignScreenTheme {
        var specialistInfo = SpecialistInfo(
            "11",
            "Kristina Sementsova",
            "nail master",
            R.drawable.fc1_short_mantras,
            3.2, 148
        )
        PersonalAccountScreen(specialistInfo)
    }
}