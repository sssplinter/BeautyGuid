package com.breaktime.signscreen.uiItems.linkButton

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity


@Composable
fun MediaIconButton(
    mediaLink: String,
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier,
    mediaLinkType: MediaLinkType = MediaLinkType.OTHER,
) {
    val context = LocalContext.current

    IconButton(modifier = modifier, onClick = {
        when (mediaLinkType) {
            MediaLinkType.TELEGRAM_LINK -> {
                val uri: Uri = Uri.parse(mediaLink)
                val telegramIntent = Intent(Intent.ACTION_VIEW, uri)

                telegramIntent.setPackage("org.telegram.messenger")

                try {
                    startActivity(context, telegramIntent, Bundle())
                } catch (e: Exception) {
                    Toast.makeText(
                        context, "Telegram app is not installed", Toast.LENGTH_LONG
                    ).show()
                }
            }
            MediaLinkType.INSTAGRAM_LINK -> {
                val uri: Uri = Uri.parse(mediaLink)
                val instagramIntent = Intent(Intent.ACTION_VIEW, uri)

                instagramIntent.setPackage("com.instagram.android")

                try {
                    startActivity(context, instagramIntent, Bundle())
                } catch (e: ActivityNotFoundException) {
                    startActivity(
                        context, Intent(
                            Intent.ACTION_VIEW, Uri.parse("http://instagram.com/")
                        ), Bundle()
                    )
                }
            }
            MediaLinkType.VK_LINK -> {
                val uri: Uri = Uri.parse(mediaLink)
                val vkIntent = Intent(Intent.ACTION_VIEW, uri)

                try {
                    startActivity(context, vkIntent, Bundle())
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                }
            }
            else -> {
            }
        }
    }) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(35.dp)
        )
    }
}

@Composable
fun MediaIconsRow(mediaLinksList: List<MediaLinkInfo>, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        for (mediaLink in mediaLinksList) {
            MediaIconButton(
                icon = mediaLink.icon,
                mediaLink = mediaLink.link,
                mediaLinkType = mediaLink.mediaLinkType
            )
        }
    }
}

data class MediaLinkInfo(
    val link: String,
    val mediaLinkType: MediaLinkType = MediaLinkType.OTHER,
    @DrawableRes val icon: Int
)

enum class MediaLinkType {
    INSTAGRAM_LINK, TELEGRAM_LINK, VK_LINK, OTHER
}