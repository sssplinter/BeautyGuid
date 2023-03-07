package com.breaktime.signscreen.screen.profile.personalAccount

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.breaktime.signscreen.R
import com.breaktime.signscreen.appComponent
import com.breaktime.signscreen.screen.profile.personalAccount.PersonalAccountContract.PersonalAccountEvent
import com.breaktime.signscreen.ui.theme.*
import com.breaktime.signscreen.uiItems.button.NormalButton
import com.breaktime.signscreen.uiItems.dialogs.IconButtonData
import com.breaktime.signscreen.uiItems.topBar.CommonTopAppBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch

@Composable
fun PersonalAccount(
    viewModel: PersonalAccountViewModel = viewModel(factory = LocalContext.current.appComponent.personalAccountViewModelFactory()),
    onSignOut: () -> Unit,
    onEditPersonalData: () -> Unit,
    onOpenAppointments: () -> Unit,
    onOpenSalons: () -> Unit,
    onOpenMasters: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val showLoadingDialog = remember { mutableStateOf(false) }

    initObservable(
        scope,
        context,
        viewModel,
        onSignOut,
        onEditPersonalData,
        onOpenAppointments,
        onOpenSalons,
        onOpenMasters,
        showLoadingDialog
    )

    Scaffold(
        topBar = {
            CommonTopAppBar(
                stringResource(R.string.personal_account),
                modifier = Modifier,
                navigationButton = null,
                IconButtonData(
                    imageVector = Icons.Default.Notifications,
                    onClick = { viewModel.setEvent(PersonalAccountEvent.OnOpenNotifications) })
            )
        }, backgroundColor = MaterialTheme.colors.BackgroundGray
    ) { paddingValues ->
        AccountScreen(Modifier.padding(paddingValues), viewModel)
    }
}

@Composable
fun AccountScreen(modifier: Modifier = Modifier, viewModel: PersonalAccountViewModel) {
    Column(
        modifier = modifier
            .padding(horizontal = 8.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Slot(
                onIconClick = { viewModel.setEvent(PersonalAccountEvent.OnEditPersonalData) },
                imageVector = Icons.Default.Edit,
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                PersonalInfo("Kristina Sementsova", "+375 44 550 93 23")
            }

            Slot(onIconClick = { viewModel.setEvent(PersonalAccountEvent.OnOpenAppointments) }) {
                Chapter(Icons.Default.DateRange, R.string.my_appointments)
            }
            Slot(onIconClick = { viewModel.setEvent(PersonalAccountEvent.OnOpenSalons) }) {
                Chapter(Icons.Default.DateRange, R.string.my_salons)
            }
            Slot(onIconClick = { viewModel.setEvent(PersonalAccountEvent.OnOpenMasters) }) {
                Chapter(Icons.Default.DateRange, R.string.my_masters)
            }
        }

        // TODO problem with navigation to login graph
        NormalButton(
            onClick = { viewModel.setEvent(PersonalAccountEvent.OnSignOut) },
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

private fun initObservable(
    composableScope: CoroutineScope,
    context: Context,
    personalAccountViewModel: PersonalAccountViewModel,
    onSignOut: () -> Unit,
    onEditPersonalData: () -> Unit,
    onOpenAppointments: () -> Unit,
    onOpenSalons: () -> Unit,
    onOpenMasters: () -> Unit,
    showLoadingDialog: MutableState<Boolean>
) {

    composableScope.launch {
        personalAccountViewModel.uiState.collect { state ->
            when (state) {
                PersonalAccountContract.PersonalAccountState.Loading -> {
                    showLoadingDialog.value = true
                }
                else -> {
                    showLoadingDialog.value = false
                }
            }
        }
    }

    composableScope.launch {
        personalAccountViewModel.effect.collect { effect ->
            composableScope.ensureActive()
            when (effect) {
                PersonalAccountContract.PersonalAccountEffect.EditPersonalData -> {
                    onEditPersonalData()
                }
                PersonalAccountContract.PersonalAccountEffect.OpenNotifications -> {
                    // TODO
                }
                PersonalAccountContract.PersonalAccountEffect.OpenAppointments -> {
                    onOpenAppointments()
                }

                PersonalAccountContract.PersonalAccountEffect.OpenSalons -> {
                    onOpenSalons()
                }
                PersonalAccountContract.PersonalAccountEffect.OpenMasters -> {
                    onOpenMasters()
                }
                is PersonalAccountContract.PersonalAccountEffect.ShowErrorMessage -> {
                    // TODO use custom dialog
                    Toast.makeText(
                        context, effect.errorMsg, Toast.LENGTH_SHORT
                    ).show()
                }
                PersonalAccountContract.PersonalAccountEffect.SignOut -> {
                    // TODO problem with navigation
//                    onSignOut()
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewAccountScreen() {
    SignScreenTheme {
//        PersonalAccount({}, {}, {}, {}, {}, {})
    }
}