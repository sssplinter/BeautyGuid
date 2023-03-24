package com.breaktime.signscreen.screen.portfolio.salonDetails

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.LockClock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.breaktime.signscreen.R
import com.breaktime.signscreen.data.network.models.SalonInfo
import com.breaktime.signscreen.ui.theme.hintColor
import com.breaktime.signscreen.ui.theme.salonH2
import com.breaktime.signscreen.uiItems.linkButton.*
import java.util.*

@Composable
fun SalonDetails(salonInfo: SalonInfo?, modifier: Modifier = Modifier) {
    salonInfo?.let {
        Column(modifier = modifier) {
            Column {
                val sectionModifier =
                    Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 6.dp)

                SlotBasedSection(
                    title = R.string.address_title,
                    imageVector = Icons.Default.LocationOn,
                    modifier = sectionModifier
                ) {
                    AddressSection(address = salonInfo.address)
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
                    ContactsSection(salonInfo)
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
fun AddressSection(address: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(start = 32.dp, end = 32.dp, top = 16.dp)) {
        // TODO address as a string
        Text(text = stringResource(id = R.string.test_address), Modifier.padding(start = 8.dp))
        LinkButton(
            link = address,
            linkText = stringResource(id = R.string.show_on_map_button),
            linkType = LinkType.ADDRESS_LINK
        )
    }
}

@Composable
fun ContactsSection(
    salonInfo: SalonInfo,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(start = 32.dp, end = 32.dp, top = 6.dp)) {
        salonInfo.phoneNumber?.let {
            LinkButton(
                link = it,
                linkType = LinkType.PHONE_NUMBER_LINK
            )
        }

        salonInfo.email?.let {
            LinkButton(
                link = it,
                linkType = LinkType.EMAIL_LINK
            )
        }

        salonInfo.webLink?.let {
            LinkButton(
                link = it,
                linkType = LinkType.WEB_SITE_LINK
            )
        }


        val icons = mutableListOf<MediaLinkInfo>()
        if (!salonInfo.telegramLink.isNullOrEmpty()) {
            icons.add(
                MediaLinkInfo(
                    link = salonInfo.telegramLink,
                    mediaLinkType = MediaLinkType.TELEGRAM_LINK,
                    icon = R.drawable.ic_telegram_color_48
                )
            )
        }
        if (!salonInfo.instagramLink.isNullOrEmpty()) {
            icons.add(
                MediaLinkInfo(
                    link = salonInfo.instagramLink,
                    mediaLinkType = MediaLinkType.INSTAGRAM_LINK,
                    icon = R.drawable.ic_instagram_color_48
                )
            )
        }

        if (!salonInfo.vkLink.isNullOrEmpty()) {
            icons.add(
                MediaLinkInfo(
                    link = salonInfo.vkLink,
                    mediaLinkType = MediaLinkType.VK_LINK,
                    icon = R.drawable.ic_vk_color_48
                )
            )
        }

        MediaIconsRow(icons)
    }
}

// TODO working hours
@Composable
fun WorkingHours(modifier: Modifier = Modifier) {
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