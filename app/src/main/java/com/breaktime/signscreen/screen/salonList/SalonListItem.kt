package com.breaktime.signscreen.screen.salonList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.breaktime.signscreen.data.entities.SalonInfo
import com.breaktime.signscreen.screen.appointments.salons.CategoryEnum
import com.breaktime.signscreen.uiItems.image.CoilImage
import com.breaktime.signscreen.uiItems.tags.NiaTopicTag
import com.breaktime.signscreen.utils.Constants
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun SalonListItem(
    salonInfo: SalonInfo,
    onSalonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .padding(vertical = 3.dp, horizontal = 4.dp)
            .fillMaxWidth()
            .clickable { },
        shape = RoundedCornerShape(15.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                ListItemRoundImage(
                    salonPhotoUrl = salonInfo.photoUrl, modifier = Modifier.size(60.dp)
                )
                Column(
                    Modifier
                        .padding(start = 12.dp)
                        .weight(0.8f)
                ) {
                    SpecialistInformation(
                        salonName = salonInfo.salonName,
                        salonDescription = salonInfo.salonDescription,
                        categoriesList = salonInfo.categories
                    )
                }
                IconButton(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    onClick = { onSalonClick() }
                ) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector = Icons.Default.ChevronRight, contentDescription = null
                    )
                }
            }
        }
    }
}

@Composable
fun ListItemRoundImage(salonPhotoUrl: String, modifier: Modifier = Modifier) {
    CoilImage(
        imageUrl = "${Constants.salonPhotoPathPrefix}$salonPhotoUrl",
        modifier = modifier.clip(CircleShape)
    )
}

@Composable
fun SpecialistInformation(
    salonName: String,
    salonDescription: String,
    categoriesList: List<String>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = salonDescription,
            style = MaterialTheme.typography.caption,
        )
        Text(
            modifier = Modifier.paddingFromBaseline(bottom = 12.dp),
            text = salonName,
            style = MaterialTheme.typography.h5.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
        )
        CategoriesSection(categoriesList)
    }
}

@Composable
fun CategoriesSection(items: List<String>, modifier: Modifier = Modifier) {
    FlowRow(modifier = modifier, mainAxisSpacing = 3.dp, crossAxisSpacing = 3.dp) {
        for (index in items.indices) {
            NiaTopicTag(
                text = CategoryEnum.valueOf(items[index]).title,
                backgroundColor = CategoryEnum.valueOf(items[index]).color
            )
        }
    }
}