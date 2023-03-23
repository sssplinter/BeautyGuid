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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.breaktime.signscreen.R
import com.breaktime.signscreen.data.entities.CategoryInfo
import com.breaktime.signscreen.data.entities.SalonInfo
import com.breaktime.signscreen.ui.theme.*
import com.breaktime.signscreen.uiItems.tags.NiaTopicTag

@Composable
fun SalonListItem(
    salonInfo: SalonInfo,
    onItemClick: () -> Unit,
    onMoreInfoClick: () -> Unit,
    onSalonClick: () -> Unit,
    onBookVisitClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClick() },
        shape = RoundedCornerShape(15.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row {
                ListItemRoundImage(
                    imageId = salonInfo.imageId, modifier = Modifier.size(70.dp)
                )
                Column(Modifier.padding(start = 16.dp)) {
                    SpecialistInformation(
                        salonName = salonInfo.salonName,
                        salonDescription = salonInfo.salonDescription,
                        categoriesList = salonInfo.categories
                    )
                }
            }

            IconButton(
                modifier = Modifier.align(Alignment.CenterVertically),
                onClick = { onMoreInfoClick() }
            ) {
                Icon(
                    modifier = Modifier
                        .size(40.dp),
                    imageVector = Icons.Default.ChevronRight, contentDescription = null
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
    salonName: String,
    salonDescription: String,
    categoriesList: List<String>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = salonDescription, style = MaterialTheme.typography.caption
        )
        Text(
            text = salonName,
            style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Medium)
        )
        CategoriesSection(categoriesList)
    }
}

@Composable
fun CategoriesSection(items: List<String>, modifier: Modifier = Modifier) {
    val colors =
        arrayOf(
            MaterialTheme.colors.PinkFromImage,
            MaterialTheme.colors.LightGreenFromImage,
            MaterialTheme.colors.VioletFromImage,
            MaterialTheme.colors.YellowFromImage
        )

    Row(modifier = modifier) {
        for (index in items.indices) {
            NiaTopicTag(
                text = { Text(items[index].uppercase()) },
                backgroundColor = colors[index]
            )
        }
    }
}

@Preview
@Composable
fun Preview() {
    SignScreenTheme {
        Column {
            SalonListItem(
                salonInfo = SalonInfo(
                    salonId = "",
                    salonName = "Marcel",
                    salonDescription = "Beauty salon and SPA",
                    imageId = R.drawable.fc5_overwhelmed,
                    categories = listOf("Massage", "Nails", "Make Up", "Hairs")
                ),
                onItemClick = { /*TODO*/ },
                onMoreInfoClick = { /*TODO*/ },
                onSalonClick = { /*TODO*/ },
                onBookVisitClick = { /*TODO*/ })

            SalonListItem(
                salonInfo = SalonInfo(
                    salonId = "",
                    salonName = "Frau Marta",
                    salonDescription = "Beauty salon",
                    imageId = R.drawable.fc3_stress_and_anxiety,
                    categories = listOf("Massage", "Make Up", "Hairs")
                ),
                onItemClick = { /*TODO*/ },
                onMoreInfoClick = { /*TODO*/ },
                onSalonClick = { /*TODO*/ },
                onBookVisitClick = { /*TODO*/ })

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