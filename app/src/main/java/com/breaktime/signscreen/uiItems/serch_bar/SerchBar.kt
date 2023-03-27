package com.breaktime.signscreen.uiItems.serch_bar

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.breaktime.signscreen.R
import com.breaktime.signscreen.ui.theme.SignScreenTheme
import com.breaktime.signscreen.ui.theme.hintColor

@Composable
fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    onClear: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .padding(3.dp)
            .fillMaxWidth()
            .height(45.dp)
            .border(
                1.dp,
                MaterialTheme.colors.hintColor,
                RoundedCornerShape(50)
            ),
        maxLines = 1,
        textStyle = TextStyle(fontSize = 12.sp),
        placeholder = { Text(stringResource(id = R.string.placeholder_search), fontSize = 12.sp) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search, contentDescription = null
            )
        },
        trailingIcon = {
            if (value.isNotEmpty()) {
                Icon(
                    modifier = Modifier.clickable { onClear() },
                    imageVector = Icons.Default.Close, contentDescription = null
                )
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(50)
    )
}

@Preview
@Composable
fun PreviewSearchBar() {
    SignScreenTheme {
        SearchBar(value = "", onValueChange = {}, onClear = { })
    }
}