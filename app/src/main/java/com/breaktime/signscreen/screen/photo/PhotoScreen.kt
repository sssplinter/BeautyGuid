package com.breaktime.signscreen.screen.photo

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.breaktime.signscreen.R
import com.breaktime.signscreen.ui.theme.*
import com.breaktime.signscreen.uiItems.ratingBar.RatingBar

@Composable
fun PortfolioScreen() {
    SignScreenTheme {
        Scaffold { paddingValues ->
            Portfolio(Modifier.padding(paddingValues))
        }
    }
}

@Composable
fun Portfolio(modifier: Modifier = Modifier) {
    Column(
        modifier
            .padding(vertical = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        SalonInfoSection(R.drawable.ab2_quick_yoga)

        Divider(
            thickness = 1.dp, color = Color.LightGray, modifier = Modifier.padding(vertical = 8.dp)
        )

        Divider(
            thickness = 1.dp, color = Color.LightGray, modifier = Modifier.padding(vertical = 8.dp)
        )
        AlignYourBodyRow()
        Divider(
            thickness = 1.dp, color = Color.LightGray, modifier = Modifier.padding(top = 8.dp)
        )
        FavoriteCollectionsGrid()
    }
}

@Composable
fun SalonInfoSection(
    @DrawableRes imageId: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
        Row(modifier = Modifier.padding(horizontal = 16.dp)) {
            Image(
                painter = painterResource(imageId),
                contentDescription = null,
                modifier = modifier
                    .size(80.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(0.9f)
                    .padding(start = 12.dp)
            ) {
                Text(
                    text = "Frau Marta",
                    style = MaterialTheme.typography.salonH6,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Beauty salon providing a wide range of beauty services",
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center
                )
                RatingBar(modifier = Modifier.padding(vertical = 4.dp), rating = 4.8)
            }
        }

        Row {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = MaterialTheme.colors.NotPrimaryText
            )
            Text(
                text = "Ave, Dodge City, USA",
                style = MaterialTheme.typography.address,
                color = MaterialTheme.colors.NotPrimaryText
            )
        }

    }
}

@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(start = 16.dp, end = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(alignYourBodyData) { item ->
            AlignYourBodyElement(imageId = item.drawable, text = item.text)
        }
    }
}

@Composable
fun AlignYourBodyElement(
    @DrawableRes imageId: Int, @StringRes text: Int, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.width(70.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TODO make better animation if needed
        val animateShape = remember { Animatable(1f) }
        LaunchedEffect(animateShape) {
            animateShape.animateTo(
                targetValue = 1f, animationSpec = repeatable(
                    animation = tween(
                        durationMillis = 800, easing = LinearEasing, delayMillis = 600
                    ), repeatMode = RepeatMode.Restart, iterations = 2
                )
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(CircleShape)
                .background(color = Color(0xFF302522))
                .border(
                    width = Dp(animateShape.value), color = Color(0xFF534A4E), shape = CircleShape
                )
        ) {
            Image(
                painter = painterResource(imageId),
                contentDescription = null,
                modifier = modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .align(Alignment.Center),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = stringResource(text),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.salonCaption,
            modifier = Modifier.paddingFromBaseline(top = 16.dp)
        )
    }
}

@Composable
fun FavoriteCollectionsGrid(
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        modifier = modifier.height(500.dp),
        content = {
            items(favoriteCollectionsData.size) { index ->
                val item = favoriteCollectionsData[index]
                FavoriteCollectionCard(
                    imageId = item.drawable, text = item.text
                )
            }
        })
}

@Composable
fun FavoriteCollectionCard(
    @DrawableRes imageId: Int, @StringRes text: Int, modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier, shape = MaterialTheme.shapes.small
    ) {
        Image(
            painter = painterResource(imageId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(120.dp)
        )
    }
}

private val alignYourBodyData = listOf(
    R.drawable.ab1_inversions to R.string.ab1_inversions,
    R.drawable.ab2_quick_yoga to R.string.ab2_quick_yoga,
    R.drawable.ab3_stretching to R.string.ab3_stretching,
    R.drawable.ab4_tabata to R.string.ab4_tabata,
    R.drawable.ab5_hiit to R.string.ab5_hiit,
    R.drawable.ab6_pre_natal_yoga to R.string.ab6_pre_natal_yoga
).map { DrawableStringPair(it.first, it.second) }

private val favoriteCollectionsData = listOf(
    R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage to R.string.fc4_self_massage,
    R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
    R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage to R.string.fc4_self_massage,
    R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
    R.drawable.fc6_nightly_wind_down to R.string.fc6_nightly_wind_down,
    R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage to R.string.fc4_self_massage,
    R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
    R.drawable.fc6_nightly_wind_down to R.string.fc6_nightly_wind_down,
    R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage to R.string.fc4_self_massage,
    R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
    R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage to R.string.fc4_self_massage,
    R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
    R.drawable.fc6_nightly_wind_down to R.string.fc6_nightly_wind_down,
    R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage to R.string.fc4_self_massage,
    R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
    R.drawable.fc6_nightly_wind_down to R.string.fc6_nightly_wind_down,
    R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage to R.string.fc4_self_massage,
    R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
    R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage to R.string.fc4_self_massage,
    R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
    R.drawable.fc6_nightly_wind_down to R.string.fc6_nightly_wind_down,
    R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage to R.string.fc4_self_massage,
    R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
    R.drawable.fc6_nightly_wind_down to R.string.fc6_nightly_wind_down
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int, @StringRes val text: Int
)

@Preview
@Composable
fun PortfolioPreview() {
    SignScreenTheme {
        PortfolioScreen()
    }
}