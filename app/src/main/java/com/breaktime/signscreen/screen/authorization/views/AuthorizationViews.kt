package com.breaktime.signscreen.screen.authorization.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.breaktime.signscreen.R
import com.breaktime.signscreen.ui.theme.ButtonShape25

@Composable
fun AuthorizationLogo(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.ic_logo),
        contentDescription = null,
        modifier = modifier
    )
}

@Composable
fun AuthorizationRedirect(
    isRegistration: Boolean,
    onRedirectToRegistration: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(id = if (isRegistration) R.string.have_account else R.string.no_account))
        Text(
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .clickable(enabled = true, onClick = { onRedirectToRegistration() }),
            text = stringResource(id = if (isRegistration) R.string.login else R.string.register),
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline
        )
    }
}

@Composable
fun AuthorizationText(isRegistration: Boolean, modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(id = if (isRegistration) R.string.registration_title else R.string.login_title),
            style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            modifier = Modifier
                .paddingFromBaseline(top = 24.dp, bottom = 24.dp)
                .padding(horizontal = 12.dp),
            text = stringResource(id = if(isRegistration) R.string.register_msg_text else  R.string.login_msg_text),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.caption.copy(fontSize = 14.sp)
        )
    }
}


@Composable
fun AuthorizationButton(
    isRegistration: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick, modifier = modifier
            .fillMaxWidth()
            .height(55.dp), shape = ButtonShape25
    ) {
        Text(
            text = stringResource(if (isRegistration) R.string.register else R.string.login),
            style = MaterialTheme.typography.subtitle1.copy(
                fontWeight = FontWeight.Bold, fontSize = 19.sp
            )
        )
    }
}