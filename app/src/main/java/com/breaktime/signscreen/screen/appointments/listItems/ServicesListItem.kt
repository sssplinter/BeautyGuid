package com.breaktime.signscreen.screen.appointments.listItems

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.breaktime.signscreen.ui.theme.NotPrimaryText

@Composable
fun ServiceListItem(
    service: String,
    price: String,
    isSelected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier,
    info: String? = null
) {
    val offset =
        animateFloatAsState(targetValue = if (isSelected) 10f else 0f, animationSpec = tween(300))

    Surface(shape = RoundedCornerShape(15.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .clickable(indication = null, interactionSource = MutableInteractionSource()) {
                onSelect()
            }) {
        Box(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .fillMaxWidth()
                .background(Color.Black)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(offset.value.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(Color.White)
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            Text(
                text = service, style = MaterialTheme.typography.h6
            )
            info?.let {
                Text(
                    text = info,
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.NotPrimaryText
                )
            }
            Row(
                modifier = Modifier.padding(top = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = { onSelect() }, modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = if (isSelected) Icons.Default.RemoveCircleOutline else Icons.Default.AddCircle,
                        modifier = Modifier.size(30.dp),
                        contentDescription = null
                    )
                }
                Text(
                    text = price,
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewServicesListItem() {
    ServiceListItem(
        "Manicure", "55.5 BYN", false, {}, info = "Removal, coating"
    )
}

