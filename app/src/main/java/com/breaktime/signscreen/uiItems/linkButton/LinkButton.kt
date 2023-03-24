package com.breaktime.signscreen.uiItems.linkButton

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity

@Composable
fun LinkButton(
    link: String,
    modifier: Modifier = Modifier,
    linkText: String = "",
    linkType: LinkType = LinkType.OTHER,
    enabled: Boolean = true
) {
    val context = LocalContext.current

    TextButton(
        onClick = {
            when (linkType) {
                LinkType.ADDRESS_LINK -> {
                    val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                    try {
                        startActivity(context, mapIntent, Bundle())
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(
                            context,
                            "No application capable of handling MAP intent",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                LinkType.PHONE_NUMBER_LINK -> {
                    val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$link"))

                    val chooserTitle =
                        "The chooser dialog forces the user to select which app to use for the action every time"
                    val chooser = Intent.createChooser(callIntent, chooserTitle)

                    try {
                        startActivity(context, chooser, Bundle())
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(
                            context,
                            "No application capable of handling PHONE intent",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                LinkType.EMAIL_LINK -> {
                    val emailIntentWithExtra =
                        Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_EMAIL, arrayOf(link)) // recipients
                            putExtra(Intent.EXTRA_SUBJECT, "Email subject")
                            putExtra(Intent.EXTRA_TEXT, "Email message text")
                            putExtra(
                                Intent.EXTRA_STREAM,
                                Uri.parse("content://path/to/email/attachment")
                            )
                        }

                    try {
                        startActivity(context, emailIntentWithExtra, Bundle())
                    } catch (e: ActivityNotFoundException) {
                        // Define what your app should do if no activity can handle the intent.
                        Toast.makeText(
                            context,
                            "No application capable of handling EMAIL intent",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                LinkType.WEB_SITE_LINK -> {
                    val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))

                    try {
                        startActivity(context, webIntent, Bundle())
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(
                            context,
                            "No application capable of handling WEB intent",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                else -> {

                }
            }
        },
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.textButtonColors(
            contentColor = Color.Blue,
        ),
        content = {
            Text(text = linkText.ifEmpty { link })
        }
    )
}

enum class LinkType {
    ADDRESS_LINK, PHONE_NUMBER_LINK, EMAIL_LINK, WEB_SITE_LINK, OTHER
}