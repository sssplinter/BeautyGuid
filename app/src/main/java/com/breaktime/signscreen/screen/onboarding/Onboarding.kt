package com.breaktime.signscreen.screen.onboarding

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.breaktime.signscreen.R
import com.breaktime.signscreen.ui.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Preview
@Composable
fun OnBoarding(
    modifier: Modifier = Modifier
) {
    val items = OnBoardingItem.get()
    val scope = rememberCoroutineScope()
    val pageState = rememberPagerState()

    val colorsCount = 5

    val selectedColorId = rememberSaveable { mutableStateOf(0) }

    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
        TopSection(
            onBackClick = {
                if (pageState.currentPage + 1 > 1) scope.launch {
                    pageState.scrollToPage(pageState.currentPage - 1)
                }
                selectedColorId.value = if (selectedColorId.value > 0)
                    selectedColorId.value - 1 else 0
            },
            onSkipClick = {
                if (pageState.currentPage + 1 < items.size) scope.launch {
                    pageState.scrollToPage(items.size - 1)
                }
                selectedColorId.value = colorsCount - 1
            }
        )

        HorizontalPager(
            count = items.size,
            state = pageState,
            modifier = Modifier
                .fillMaxHeight(0.85f)
                .fillMaxWidth(),
            userScrollEnabled = false
        ) { page ->
            // TODO currently user scroll is disabled because selectedColorId change
            //  here cause endless recomposition and unstable color change
            OnBoardingItem(items = items[page], selectedColorId.value)
        }

        BottomSection(size = items.size, index = pageState.currentPage) {
            if (pageState.currentPage + 1 < items.size) scope.launch {
                pageState.scrollToPage(pageState.currentPage + 1)
            }
            selectedColorId.value = if (selectedColorId.value < colorsCount - 1)
                selectedColorId.value + 1 else colorsCount - 1
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TopSection(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onSkipClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        // Back button
        IconButton(onClick = onBackClick, modifier = Modifier.align(Alignment.CenterStart)) {
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowLeft,
                contentDescription = null,
                modifier = Modifier.size(36.dp)
            )
        }

        // Skip Button
        TextButton(
            onClick = onSkipClick,
            modifier = Modifier.align(Alignment.CenterEnd),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = stringResource(id = R.string.skip),
                color = MaterialTheme.colors.onBackground,
                fontSize = 20.sp,
                style = TextStyle(textDecoration = TextDecoration.Underline)
            )
        }
    }
}

@Composable
fun BottomSection(
    size: Int, index: Int, modifier: Modifier = Modifier, onButtonClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp, start = 24.dp, end = 24.dp)
    ) {
        // Indicators
        Indicators(size, index)

        FloatingActionButton(
            onClick = { onButtonClick() },
            backgroundColor = MaterialTheme.colors.DarkGreenFromImage,
            modifier = Modifier
                .padding(vertical = 12.dp)
                .align(Alignment.CenterEnd)
        ) {
            Icon(
                Icons.Outlined.KeyboardArrowRight,
                tint = Color.White,
                contentDescription = stringResource(R.string.onboarding_next)
            )
        }
    }
}

@Composable
fun BoxScope.Indicators(
    size: Int, index: Int, modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.align(Alignment.CenterStart)
    ) {
        repeat(size) {
            Indicator(isSelected = it == index)
        }
    }
}

@Composable
fun Indicator(
    isSelected: Boolean, modifier: Modifier = Modifier
) {
    val width = animateDpAsState(
        targetValue = if (isSelected) 25.dp else 10.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )
    Box(
        modifier = modifier
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                color = if (isSelected) MaterialTheme.colors.primary else Color(0XFFF8E2E7)
            )
    ) {
    }
}

@Composable
fun OnBoardingItem(
    items: OnBoardingItem, selectedColorId: Int, modifier: Modifier = Modifier
) {
    val colors =
        arrayOf(
            MaterialTheme.colors.PinkFromImage,
            MaterialTheme.colors.OrangeFromImage,
            MaterialTheme.colors.BlueFromImage,
            MaterialTheme.colors.YellowFromImage,
            MaterialTheme.colors.VioletFromImage
        )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(
                    R.drawable.v_background_1
                ),
                alpha = 0.9f,
                colorFilter = ColorFilter.tint(color = colors[selectedColorId]),
                contentDescription = "Image1"
            )
            Image(
                painter = painterResource(id = items.image),
                contentDescription = "Image1",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(25))
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = stringResource(id = items.title),
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            letterSpacing = 1.sp,
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(id = items.text),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onBackground,
            fontSize = 18.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(10.dp),
            letterSpacing = 1.sp,
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun Preview1() {
    SignScreenTheme {
        OnBoarding()
    }
}