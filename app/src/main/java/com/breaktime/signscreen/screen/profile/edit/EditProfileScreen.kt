@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.breaktime.signscreen.screen.profile.edit

import android.Manifest
import android.content.Context
import android.graphics.Bitmap
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.breaktime.signscreen.R
import com.breaktime.signscreen.screen.profile.BottomDrawerItem
import com.breaktime.signscreen.screen.profile.ContactsSection
import com.breaktime.signscreen.screen.profile.PersonalDataSection
import com.breaktime.signscreen.screen.profile.ProfileSlotBasedSection
import com.breaktime.signscreen.screen.profile.common.ProfileContract
import com.breaktime.signscreen.ui.theme.SignScreenTheme
import com.breaktime.signscreen.uiItems.button.NormalButton
import com.breaktime.signscreen.uiItems.inputFields.*
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalPermissionsApi::class)
@Composable
fun EditProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: EditProfileViewModel = viewModel(),
    onNavigateBack: () -> Unit,
    onSuccessfullyEdit: () -> Unit
) {
    val gesturesEnabled = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val drawerState = rememberBottomDrawerState(BottomDrawerValue.Closed)

    val context = LocalContext.current
    val cameraPermission = rememberPermissionState(Manifest.permission.CAMERA)
    var isPermissionRequested by rememberSaveable { mutableStateOf(false) }

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) { uriList ->
            scope.launch {
                drawerState.close()
            }
            viewModel.onImageChange(uriList.first())
            gesturesEnabled.value = false
        }

    val cameraLauncher: ManagedActivityResultLauncher<Void?, Bitmap?> =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
            bitmap?.let {
                scope.launch {
                    drawerState.close()
                }
                viewModel.onImageChange(getImageUri(context, it))
                gesturesEnabled.value = false
            }
        }

    val surname = viewModel.surname
    val name = viewModel.name
    val avatarUri = viewModel.avatarUri
    val email = viewModel.email
    val mobileNumber = viewModel.mobileNumber

    val showLoadingDialog = remember { mutableStateOf(false) }

    initObservable(
        scope,
        gesturesEnabled,
        drawerState,
        context,
        viewModel,
        onNavigateBack,
        onSuccessfullyEdit,
        showLoadingDialog
    )

    // TODO default profile image
//    val uri = getUriToDrawable(context, R.drawable.avata)
//    viewModel.onImageChange(uri)

    BottomDrawer(
        gesturesEnabled = gesturesEnabled.value,
        drawerState = drawerState,
        drawerContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(
                    text = stringResource(R.string.choose_image),
                    style = MaterialTheme.typography.subtitle2.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.SansSerif
                    ),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                BottomDrawerItem(
                    Icons.Default.Image,
                    stringResource(R.string.choose_from_gallery),
                    {
                        galleryLauncher.launch("image/*")

                    })

                BottomDrawerItem(Icons.Default.PhotoCamera, stringResource(R.string.camera), {
                    if (!cameraPermission.hasPermission) {
                        cameraPermission.launchPermissionRequest()
                        isPermissionRequested = true
                    } else cameraLauncher.launch()
                })
            }
        },
        content = {
            Column(
                modifier = modifier
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center
            ) {
                ImagePickerView(
                    lastSelectedImage = avatarUri,
                    onSelection = {},
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 16.dp),
                    onClick = {
                        viewModel.setEvent(ProfileContract.ProfileEvent.OnChoosePhotoClick)
                    })

                ProfileSlotBasedSection(titleRes = R.string.personal_data) {
                    PersonalDataSection(
                        surname = surname,
                        name = name,
                        onSurnameValueChange = { value -> viewModel.onSurnameValueChange(value) },
                        onNameValueChange = { value -> viewModel.onNameValueChange(value) }
                    )
                }

                ProfileSlotBasedSection(titleRes = R.string.contacts_title) {
                    ContactsSection(
                        email = email,
                        mobileNumber = mobileNumber,
                        onEmailValueChange = { value -> viewModel.onEmailValueChange(value) },
                        onMobileNumberValueChange = { value ->
                            viewModel.onMobileNumberValueChange(
                                value
                            )
                        }
                    )
                }

                NormalButton(
                    onClick = { viewModel.setEvent(ProfileContract.ProfileEvent.OnSaveClick) },
                    textRes = R.string.save_btn,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
private fun initObservable(
    composableScope: CoroutineScope,
    gesturesEnabled: MutableState<Boolean>,
    drawerState: BottomDrawerState,
    context: Context,
    editProfileViewModel: EditProfileViewModel,
    onNavigateBack: () -> Unit,
    onSuccessfullyEdit: () -> Unit,
    showLoadingDialog: MutableState<Boolean>
) {

    composableScope.launch {
        editProfileViewModel.uiState.collect { state ->
            when (state) {
                ProfileContract.ProfileState.Loading -> {
                    showLoadingDialog.value = true
                }
                else -> {
                    showLoadingDialog.value = false
                }
            }
        }
    }

    composableScope.launch {
        editProfileViewModel.effect.collect { effect ->
            composableScope.ensureActive()
            when (effect) {
                ProfileContract.ProfileEffect.ChoosePhoto -> {
                    gesturesEnabled.value = true
                    composableScope.launch {
                        drawerState.open()
                    }
                }
                ProfileContract.ProfileEffect.NavigateBack -> {
                    onNavigateBack()
                }
                is ProfileContract.ProfileEffect.ShowErrorMessage -> {
                    Toast.makeText(
                        context, effect.errorMsg, Toast.LENGTH_SHORT
                    ).show()
                }
                ProfileContract.ProfileEffect.SuccessfulEdit -> {
                    onSuccessfullyEdit()
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewEditProfileScreen() {
    SignScreenTheme {
        EditProfileScreen(onNavigateBack = {}, onSuccessfullyEdit = {})
    }
}
