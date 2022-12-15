package com.breaktime.signscreen.screen.profile

import android.Manifest
import android.graphics.Bitmap
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.breaktime.signscreen.R
import com.breaktime.signscreen.uiItems.inputFields.*
import com.breaktime.signscreen.utils.getUriToDrawable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalPermissionsApi::class)
@Composable
fun EditProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: EditProfileViewModel = viewModel()
) {
    val gesturesEnabled = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val drawerState = rememberBottomDrawerState(BottomDrawerValue.Closed)


    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) { uriList ->
            scope.launch {
                drawerState.close()
            }
            viewModel.onImageChange(uriList.first())
            gesturesEnabled.value = false
        }

    val context = LocalContext.current
    val cameraPermission = rememberPermissionState(Manifest.permission.CAMERA)
    var isPermissionRequested by rememberSaveable { mutableStateOf(false) }

//    val uri = getUriToDrawable(context, R.drawable.avata)
//    viewModel.onImageChange(uri)

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
                BottomDrawerItem(
                    Icons.Default.PhotoCamera,
                    stringResource(R.string.camera),
                    {
                        if (!cameraPermission.hasPermission) {
                            cameraPermission.launchPermissionRequest()
                            isPermissionRequested = true
                        } else
                            cameraLauncher.launch()
                    }
                )
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
                    lastSelectedImage = viewModel.avatarUri,
                    onSelection = {
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 16.dp),
                    onClick = {
                        gesturesEnabled.value = true
                        scope.launch { drawerState.open() }
                    }
                )

                ChapterDeliverText(
                    stringResource(R.string.personal_data)
                )
                NameInputField(
                    nameValue = viewModel.surname,
                    label = stringResource(R.string.surname_placeholder),
                    onValueChange = { value ->
                        viewModel.onSurnameValueChange(value)
                    })
                NameInputField(
                    nameValue = viewModel.name,
                    label = stringResource(R.string.name_placeholder),
                    onValueChange = { value ->
                        viewModel.onNameValueChange(value)
                    })
                NameInputField(
                    nameValue = viewModel.patronymic,
                    label = stringResource(R.string.patronymic_placeholder),
                    onValueChange = { value ->
                        viewModel.onPatronymicValueChange(value)
                    })
                DataPickerTextField(viewModel)

                ChapterDeliverText(stringResource(R.string.contacts))
                MobileNumberField(viewModel)
                EmailField(
                    viewModel.email,
                    { value -> viewModel.onEmailValueChange(value) })

                Button(
                    onClick = { },
                    modifier = modifier
                        .padding(top = 16.dp, bottom = 32.dp)
                        .fillMaxWidth()
                        .height(45.dp),
                    shape = MaterialTheme.shapes.small,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Black,
                        contentColor = Color.White
                    ),
                    // border = BorderStroke(1.dp, MaterialTheme.colors.primaryVariant)
                ) {
                    Text(
                        stringResource(R.string.save_btn),
                        style = MaterialTheme.typography.button.copy(fontSize = 16.sp)
                    )
                }
            }
        }
    )
}

@Composable
fun ChapterDeliverText(chapter: String, modifier: Modifier = Modifier) {
    Text(
        text = chapter,
        style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold),
        modifier = modifier.padding(top = 16.dp, bottom = 4.dp)
    )
}

@Composable
fun BottomDrawerItem(
    imageVector: ImageVector,
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(onClick = { onClick() }, modifier = modifier.fillMaxWidth()) {
        Icon(
            imageVector,
            modifier = Modifier.padding(end = 8.dp),
            contentDescription = stringResource(R.string.choose_from_gallery)
        )
        Text(text = title)
    }
}

@Preview
@Composable
fun PreviewEditProfileScreen() {
    EditProfileScreen()
}
