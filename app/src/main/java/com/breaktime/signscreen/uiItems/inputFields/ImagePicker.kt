package com.breaktime.signscreen.uiItems.inputFields

import android.Manifest
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import java.io.ByteArrayOutputStream

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ImagePickerView(
    modifier: Modifier = Modifier,
    lastSelectedImage: Uri?,
    onSelection: (Uri?) -> Unit
) {
    val context = LocalContext.current
    val cameraPermission = rememberPermissionState(Manifest.permission.CAMERA)
    var isPermissionRequested by rememberSaveable { mutableStateOf(false) }

    val cameraLauncher: ManagedActivityResultLauncher<Void?, Bitmap?> =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
            bitmap?.let {
                onSelection(getImageUri(context, it))
            }
        }

    if (isPermissionRequested && cameraPermission.hasPermission) {
        cameraLauncher.launch()
        isPermissionRequested = false
    }

    Image(
        modifier = modifier
            .size(85.dp)
            .clip(CircleShape)
            .border(
                width = 1.5.dp, color = Color.Black, shape = CircleShape
            )
            .background(Color.LightGray)
            .clickable {
                if (!cameraPermission.hasPermission) {
                    cameraPermission.launchPermissionRequest()
                    isPermissionRequested = true
                } else
                    cameraLauncher.launch()
            },
        painter = rememberImagePainter(lastSelectedImage),
        contentDescription = "Profile Picture",
        contentScale = ContentScale.Crop
    )
}

// TODO move to UTILS
fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
    val bytes = ByteArrayOutputStream()
    inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
    val path =
        MediaStore.Images.Media.insertImage(inContext.contentResolver, inImage, "Title", null)
    return Uri.parse(path)
}

@Preview
@Composable
fun ImagePickerPreview() {

    val builder = Uri.Builder()
    val myUrl = builder.build()

    val lastSelectedImage = rememberSaveable {
        mutableStateOf(myUrl)
    }
    ImagePickerView(
        lastSelectedImage = lastSelectedImage.value,
        onSelection = { lastSelectedImage.value = it })
}