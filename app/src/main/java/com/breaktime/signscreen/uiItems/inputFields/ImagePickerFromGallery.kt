package com.breaktime.signscreen.uiItems.inputFields

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun ImagePickerFromGallery(
    modifier: Modifier = Modifier,
    lastSelectedImage: Uri?,
    onSelection: (Uri?) -> Unit
) {
    val context = LocalContext.current

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) { uriList ->
            // process eith the received image uri
            onSelection(uriList.first())
        }

//    val cameraLauncher: ManagedActivityResultLauncher<Void?, Bitmap?> =
//        rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
//            bitmap?.let {
//                onSelection(getImageUri(context, it))
//            }
//        }

//    if (isPermissionRequested && cameraPermission.hasPermission)
//    {
//    galleryLauncher.launch("image/*")
//        isPermissionRequested = false
//    }

    Image(
        modifier = modifier
            .size(85.dp)
            .clip(CircleShape)
            .border(
                width = 1.dp, color = Color.Black, shape = CircleShape
            )
            .background(Color.LightGray)
            .clickable
            {
//                if (!cameraPermission.hasPermission) {
//                    cameraPermission.launchPermissionRequest()
//                    isPermissionRequested = true
//                } else
                galleryLauncher.launch("image/*")
            },

        painter = rememberImagePainter(lastSelectedImage),
        contentDescription = "Profile Picture",
        contentScale = ContentScale.Crop
    )
}

//// TODO move to UTILS
//fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
//    val bytes = ByteArrayOutputStream()
//    inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
//    val path =
//        MediaStore.Images.Media.insertImage(inContext.contentResolver, inImage, "Title", null)
//    return Uri.parse(path)
//}