package com.breaktime.signscreen.screen.profile

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.breaktime.signscreen.R
import com.breaktime.signscreen.ui.theme.*
import com.breaktime.signscreen.uiItems.button.NormalButton
import com.breaktime.signscreen.uiItems.dialogs.IconButtonData
import com.breaktime.signscreen.uiItems.topBar.CommonTopAppBar

@Composable
fun PersonalAccount(
    onSignOut: () -> Unit,
    onPersonalDataEdit: () -> Unit,
    onOpenAppointments: () -> Unit,
    onOpenSalons: () -> Unit,
    onOpenMasters: () -> Unit
) {
    Scaffold(
        topBar = {
            CommonTopAppBar(
                stringResource(R.string.personal_account),
                modifier = Modifier,
                navigationButton = null,
                IconButtonData(imageVector = Icons.Default.Notifications, onClick = {})
            )
        }, backgroundColor = MaterialTheme.colors.BackgroundGray
    ) { paddingValues ->
        AccountScreen(Modifier.padding(paddingValues), onPersonalDataEdit)
    }
}

@Composable
fun AccountScreen(modifier: Modifier = Modifier, onPersonalDataEdit: () -> Unit) {
    Column(
        modifier = modifier
            .padding(horizontal = 8.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Slot(
                onIconClick = onPersonalDataEdit,
                imageVector = Icons.Default.Edit,
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                PersonalInfo("Kristina Sementsova", "+375 44 550 93 23")
            }

            Slot(onIconClick = {}) {
                Chapter(Icons.Default.DateRange, R.string.my_appointments)
            }
            Slot(onIconClick = {}) {
                Chapter(Icons.Default.DateRange, R.string.my_salons)
            }
            Slot(onIconClick = {}) {
                Chapter(Icons.Default.DateRange, R.string.my_masters)
            }
        }

        NormalButton(
            onClick = { },
            textRes = R.string.sign_out,
            modifier = Modifier.padding(vertical = 32.dp)
        )
    }
}

@Composable
fun PersonalInfo(name: String, contact: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier.padding(vertical = 16.dp)) {
        Image(
            painter = painterResource(R.drawable.ab2_quick_yoga),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.padding(start = 16.dp, end = 8.dp)) {
            Text(
                text = name,
                style = MaterialTheme.typography.salonH6.copy(fontSize = 20.sp),
                textAlign = TextAlign.Center
            )
            Text(
                text = contact,
                style = MaterialTheme.typography.body1.copy(fontSize = 13.sp),
                color = MaterialTheme.colors.NotPrimaryText,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun Chapter(icon: ImageVector, @StringRes title: Int, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Icon(
            imageVector = icon, tint = MaterialTheme.colors.hintColor, contentDescription = null
        )
        Text(
            text = stringResource(id = title),
            modifier = Modifier.padding(horizontal = 16.dp),
            style = MaterialTheme.typography.body1.copy(
                fontWeight = FontWeight.W500,
            )
        )
    }
}

@Composable
fun Slot(
    onIconClick: () -> Unit,
    modifier: Modifier = Modifier,
    imageVector: ImageVector = Icons.Default.ChevronRight,
    content: @Composable () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(15.dp),
        modifier = modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        Row(
            modifier = Modifier.padding(start = 20.dp, end = 12.dp, top = 8.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(0.9f)) {
                content()
            }

            IconButton(
                onClick = { onIconClick() }, modifier = Modifier.size(55.dp)
            ) {
                Icon(
                    imageVector = imageVector,
                    tint = MaterialTheme.colors.hintColor,
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewAccountScreen() {
    SignScreenTheme {
        PersonalAccount({}, {}, {}, {}, {})
    }
}