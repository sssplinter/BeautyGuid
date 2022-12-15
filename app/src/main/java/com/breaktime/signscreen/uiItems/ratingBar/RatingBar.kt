package com.breaktime.signscreen.uiItems.ratingBar

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.breaktime.signscreen.ui.theme.StarYellow
import com.breaktime.signscreen.ui.theme.NoRatingColor
import kotlin.math.ceil
import kotlin.math.min

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color = if (rating > 0.0) MaterialTheme.colors.StarYellow else MaterialTheme.colors.NoRatingColor,
) {
    val filledStars = min(ceil(rating).toInt(), stars)
    val unfilledStars = (stars - filledStars)

    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(imageVector = Icons.Outlined.Star, contentDescription = null, tint = starsColor)
        }

        repeat(unfilledStars) {
            Icon(
                imageVector = Icons.Outlined.StarOutline,
                contentDescription = null,
                tint = starsColor
            )
        }
    }
}

@Preview
@Composable
fun ZeroMarksRatingPreview() {
    RatingBar(rating = 0.0)
}

@Preview
@Composable
fun RatingPreview() {
    RatingBar(rating = 2.8)
}

@Preview
@Composable
fun MaxRatingPreview() {
    RatingBar(rating = 10.8)
}