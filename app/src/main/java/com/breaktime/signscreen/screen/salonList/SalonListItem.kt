package com.breaktime.signscreen.screen.salonList

import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.breaktime.signscreen.data.entities.CategoryInfo
import com.breaktime.signscreen.data.entities.SalonInfo
import com.breaktime.signscreen.ui.theme.LightGreenFromImage
import com.breaktime.signscreen.ui.theme.PinkFromImage
import com.breaktime.signscreen.ui.theme.VioletFromImage
import com.breaktime.signscreen.ui.theme.YellowFromImage
import com.breaktime.signscreen.uiItems.tags.NiaTopicTag
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
                    imageId = salonInfo.imageId, modifier = Modifier.size(60.dp)
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

// TODO replace drawable by real image
// TODO move to utils, make common
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

// TODO organize colors
@Composable
fun CategoriesSection(items: List<String>, modifier: Modifier = Modifier) {
    val colors =
        arrayOf(
            MaterialTheme.colors.PinkFromImage,
            MaterialTheme.colors.LightGreenFromImage,
            MaterialTheme.colors.VioletFromImage,
            MaterialTheme.colors.YellowFromImage
        )

    FlowRow(modifier = modifier, mainAxisSpacing = 3.dp, crossAxisSpacing = 3.dp) {
        for (index in items.indices) {
            NiaTopicTag(
                text = items[index],
                backgroundColor = colors[index % colors.size]
            )
        }
    }
}

sealed class ServicesCategory(
    val categoryId: Int = 0,
    val category: String,
    val color: Color
) {
    class MassageCategory : ServicesCategory(0, "Massage", Color(0xFFF5A7A9))
    class HairCategory : ServicesCategory(1, "Hair", Color(0xFFEEDE71))
    class NailsCategory : ServicesCategory(2, "Nails", Color(0xFF87b0a7))
    class SPACategory : ServicesCategory(3, "SPA", Color(0xFFFF9e8d))
    class MakeUpCategory : ServicesCategory(4, "Make Up", Color(0xFFd3acc9))
    class SkinCareCategory : ServicesCategory(5, "Skin care", Color(0xFF8BCED6))
}

val categories = listOf<CategoryInfo>(
    CategoryInfo("Massage", Color(0xFFF5A7A9))
)