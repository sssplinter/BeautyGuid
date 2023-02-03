package com.breaktime.signscreen.screen.portfolio

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.breaktime.signscreen.R
import com.breaktime.signscreen.ui.theme.SignScreenTheme
import com.breaktime.signscreen.ui.theme.hintColor
import com.breaktime.signscreen.ui.theme.salonH2
import com.breaktime.signscreen.uiItems.linkButton.LinkButton
import java.util.*

@Composable
fun SalonDetails(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Column {
            val sectionModifier =
                Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 6.dp)

            SlotBasedSection(
                title = R.string.address_title,
                imageVector = Icons.Default.LocationOn,
                modifier = sectionModifier
            ) {
                AddressSection()
            }

            Divider(
                thickness = 1.dp,
                color = Color.LightGray,
            )

            SlotBasedSection(
                title = R.string.contacts_title,
                imageVector = Icons.Default.Phone,
                modifier = sectionModifier
            ) {
                ContactsSection()
            }

            Divider(
                thickness = 1.dp,
                color = Color.LightGray,
            )

            SlotBasedSection(
                title = R.string.working_hours_title,
                imageVector = Icons.Default.LockClock,
                modifier = sectionModifier
            ) {
                WorkingHours()
            }

            Divider(
                thickness = 1.dp,
                color = Color.LightGray,
            )
        }
    }
}

@Composable
fun SlotBasedSection(
    @StringRes title: Int,
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = imageVector,
                modifier = Modifier.size(24.dp),
                contentDescription = null,
                tint = MaterialTheme.colors.hintColor
            )

            Text(
                text = stringResource(title).uppercase(Locale.getDefault()),
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.salonH2
            )
        }

        content()
    }
}

@Composable
fun AddressSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(start = 32.dp, end = 32.dp, top = 16.dp)) {
        Text(text = stringResource(id = R.string.test_address), Modifier.padding(start = 8.dp))
        LinkButton(onClick = {}) {
            Text(text = stringResource(id = R.string.show_on_map_button))
        }
    }
}

@Composable
fun ContactsSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(start = 32.dp, end = 32.dp, top = 6.dp)) {
        LinkButton(onClick = {}) {
            Text(text = stringResource(id = R.string.test_phone_number_button))
        }

        LinkButton(onClick = {}) {
            Text(text = stringResource(id = R.string.test_web_site))
        }
        MediaIconsRow()
    }
}

@Composable
fun MediaIconsRow(modifier: Modifier = Modifier) {
    val icons = listOf<Int>(
        R.drawable.ic_telegram, R.drawable.ic_instagram, R.drawable.ic_vk, R.drawable.ic_viber
    )
    Row() {
        for (icon in icons) {
            MediaIconButton(icon = icon)
        }
    }
}

// TODO add intents with link
@Composable
fun MediaIconButton(@DrawableRes icon: Int, modifier: Modifier = Modifier) {
    IconButton(onClick = { /*TODO intent to*/ }) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = modifier
                .size(43.dp)
                .padding(4.dp),
            tint = Color.DarkGray
        )
    }
}

@Composable
fun WorkingHours(modifier: Modifier = Modifier) {
    // TODO
    val tempHours = listOf<Pair<String, String>>(
        Pair("Monday-Friday", "10:00 - 20:00"), Pair("Saturday-Sunday", "11:00 - 19:00")
    )
    Column(
        modifier = modifier.padding(start = 40.dp, end = 32.dp, top = 16.dp, bottom = 6.dp)
    ) {
        for (hours in tempHours) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = hours.first)
                Text(text = hours.second)
            }
        }
    }
}

@Preview
@Composable
fun PreviewSlotSection() {
    SignScreenTheme {
        Column {
            val sectionModifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)

            SlotBasedSection(
                title = R.string.address_title,
                imageVector = Icons.Default.LocationOn,
                modifier = sectionModifier
            ) {
                AddressSection()
            }
        }
    }
}