package com.breaktime.signscreen.screen.portfolio.photo

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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.breaktime.signscreen.R
import com.breaktime.signscreen.data.entities.SpecialistInfo
import com.breaktime.signscreen.data.network.models.SalonNewsPreview
import com.breaktime.signscreen.data.source.salonApi.remote.SalonPreviewResponse
import com.breaktime.signscreen.screen.appointments.specialists.SpecialistsList
import com.breaktime.signscreen.screen.portfolio.SalonDetailsInfo
import com.breaktime.signscreen.screen.portfolio.salonDetails.SalonDetails
import com.breaktime.signscreen.ui.theme.*
import com.breaktime.signscreen.uiItems.image.CoilImage
import com.breaktime.signscreen.uiItems.ratingBar.RatingBar
import com.breaktime.signscreen.utils.Constants
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Portfolio(
    modifier: Modifier = Modifier,
    salonPreview: SalonPreviewResponse?,
    salonInfo: SalonDetailsInfo?,
    salonsNewsPreviews: List<SalonNewsPreview>,
    salonSpecialists: List<SpecialistInfo>,
    onPhotoClick: (Int) -> Unit
) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    val pages = mutableListOf(
        Icons.Default.Image, Icons.Default.Notes, Icons.Default.Person
    )

    Column(
        modifier
            .padding(vertical = 8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        SalonInfoSection(salonPreview)

        TabRow(
            selectedTabIndex = pagerState.currentPage, indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            }, backgroundColor = MaterialTheme.colors.onPrimary
        ) {
            pages.forEachIndexed { index, vector ->
                Tab(
                    icon = {
                        IconButton(
                            // TODO
                            onClick = {
                                scope.launch {
                                    pagerState.scrollToPage(index)
                                }
                            },
                            modifier = Modifier.height(30.dp),
                        ) {
                            Icon(
                                imageVector = vector, contentDescription = null
                            )
                        }
                    },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.scrollToPage(index)
                        }
                    },
                )
            }
        }

        HorizontalPager(
            count = pages.size,
            state = pagerState,
        ) { page ->
            when (pages[page]) {
                Icons.Default.Image -> {
                    Column {
                        AlignYourBodyRow()
                        Divider(
                            thickness = 1.dp,
                            color = Color.LightGray,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                        FavoriteCollectionsGrid(
                            salonNewsPreviews = salonsNewsPreviews,
                            onPhotoClick = onPhotoClick
                        )
                    }
                }
                Icons.Default.Notes -> {
                    SalonDetails(salonInfo = salonInfo)
                }
                Icons.Default.Person -> {
                    SpecialistsList(
                        specialists = salonSpecialists,
                        onBookVisitClick = {},
                        onSalonClick = {},
                        onMoreInfoClick = {},
                        modifier = Modifier.height(500.dp).fillMaxWidth()
                    )
                }
                else -> {
                    Text(text = "Page $page")

                }
            }
        }
    }
}

@Composable
fun SalonInfoSection(
    salonPreview: SalonPreviewResponse?, modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
        Row(modifier = Modifier.padding(start = 30.dp, end = 30.dp, top = 6.dp)) {
            CoilImage(
                imageUrl = "${Constants.salonPhotoPathPrefix}${salonPreview?.salonPhotoUrl}",
                modifier = Modifier
                    .size(65.dp)
                    .clip(CircleShape),
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(0.9f)
                    .padding(start = 12.dp)
            ) {
                Text(
                    text = salonPreview?.salonDescription ?: "Beauty salon",
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center
                )

                RatingBar(
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .height(20.dp), rating = salonPreview?.rating ?: 5.0
                )

                salonPreview?.location?.let {
                    Row {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = MaterialTheme.colors.NotPrimaryText
                        )
                        Text(
                            text = it,
                            style = MaterialTheme.typography.address,
                            color = MaterialTheme.colors.NotPrimaryText
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.padding(top = 12.dp),
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
        modifier = modifier.width(60.dp), horizontalAlignment = Alignment.CenterHorizontally
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
                    .size(60.dp)
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
    salonNewsPreviews: List<SalonNewsPreview>,
    modifier: Modifier = Modifier,
    onPhotoClick: (Int) -> Unit
) {
    LazyVerticalGrid(columns = GridCells.Fixed(4),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        modifier = modifier.height(500.dp),
        content = {
            items(salonNewsPreviews.size) { index ->
                CoilImage(
                    imageUrl = "${Constants.salonNewsPhotoPrefix}${salonNewsPreviews[index].photoUrl}",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .clickable { onPhotoClick(index) }
                        .clip(MaterialTheme.shapes.small)
                )
            }
        })
}

@Composable
fun FavoriteCollectionCard(
    @DrawableRes imageId: Int, modifier: Modifier = Modifier, onPhotoClick: () -> Unit
) {
    Surface(
        modifier = modifier.clickable { onPhotoClick() }, shape = MaterialTheme.shapes.small
    ) {
        Image(
            painter = painterResource(imageId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.aspectRatio(1f)
        )
    }
}

// TODO replace by real data
private val alignYourBodyData = listOf(
    R.drawable.ab1_inversions to R.string.ab1_inversions,
    R.drawable.ab2_quick_yoga to R.string.ab2_quick_yoga,
    R.drawable.ab3_stretching to R.string.ab3_stretching,
    R.drawable.ab4_tabata to R.string.ab4_tabata,
    R.drawable.ab5_hiit to R.string.ab5_hiit,
    R.drawable.ab6_pre_natal_yoga to R.string.ab6_pre_natal_yoga
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int, @StringRes val text: Int
)