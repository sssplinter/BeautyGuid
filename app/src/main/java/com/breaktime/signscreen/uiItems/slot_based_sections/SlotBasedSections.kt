package com.breaktime.signscreen.uiItems.slot_based_sections

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import java.util.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.breaktime.signscreen.R
import com.breaktime.signscreen.screen.portfolio.photo.AlignYourBodyRow
import com.breaktime.signscreen.screen.portfolio.photo.FavoriteCollectionsGrid

// TODO how to use
@Composable
fun SlotBasedSections(modifier: Modifier = Modifier) {
    Column(
        modifier
            .padding(vertical = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        SlotBasedHomeSection(title = R.string.align_your_body) {
            AlignYourBodyRow()
        }
        SlotBasedHomeSection(title = R.string.favorite_collections) {
            FavoriteCollectionsGrid()
        }
    }
}

@Composable
fun SlotBasedHomeSection(
    @StringRes title: Int, modifier: Modifier = Modifier, content: @Composable () -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(title).uppercase(Locale.getDefault()),
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp),
            style = MaterialTheme.typography.h2
        )
        content()
    }
}