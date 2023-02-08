package com.breaktime.signscreen.screen.portfolio.salonDetails

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.breaktime.signscreen.R
import com.breaktime.signscreen.ui.theme.SignScreenTheme
import com.breaktime.signscreen.ui.theme.hintColor
import com.breaktime.signscreen.ui.theme.salonH2
import com.breaktime.signscreen.uiItems.linkButton.*
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
        LinkButton(
            link = stringResource(id = R.string.show_on_map_button),
            linkType = LinkType.ADDRESS_LINK
        )
    }
}

@Composable
fun ContactsSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(start = 32.dp, end = 32.dp, top = 6.dp)) {
        LinkButton(
            link = stringResource(
                id = R.string.test_phone_number_button
            ),
            linkType = LinkType.PHONE_NUMBER_LINK
        )

        LinkButton(
            link = stringResource(id = R.string.test_email_address),
            linkType = LinkType.EMAIL_LINK
        )


        LinkButton(
            link = stringResource(id = R.string.test_web_site),
            linkType = LinkType.WEB_SITE_LINK
        )

        // TODO replace by real data
        val icons = listOf<MediaLinkInfo>(
            MediaLinkInfo(
                link = stringResource(id = R.string.test_telegram_link),
                mediaLinkType = MediaLinkType.TELEGRAM_LINK,
                icon = R.drawable.ic_telegram
            ),
            MediaLinkInfo(
                link = stringResource(id = R.string.test_instagram_link),
                mediaLinkType = MediaLinkType.INSTAGRAM_LINK,
                icon = R.drawable.ic_instagram
            ),
            MediaLinkInfo(
                link = stringResource(id = R.string.test_vk_link),
                mediaLinkType = MediaLinkType.VK_LINK,
                icon = R.drawable.ic_vk
            )
        )

        MediaIconsRow(icons)
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