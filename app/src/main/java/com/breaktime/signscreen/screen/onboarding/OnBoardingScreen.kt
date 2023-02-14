package com.breaktime.signscreen.screen.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.breaktime.signscreen.R
import com.breaktime.signscreen.ui.theme.EndRoundedButtonShape25
import com.breaktime.signscreen.ui.theme.SignScreenTheme
import com.breaktime.signscreen.ui.theme.StartRoundedButtonShape25

@Composable
fun OnBoardingScreen(
    onNavigateToSignIn: () -> Unit,
    onNavigateToRegister: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12))
                .background(MaterialTheme.colors.primary)
                .weight(1f)
                .padding(8.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            OnBoardingImage()
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                OnBoardingText()
                Buttons({ onNavigateToSignIn() },
                    { onNavigateToRegister() })
            }
        }
    }
}

@Composable
fun OnBoardingImage(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier,
        alignment = Alignment.Center,
        contentScale = ContentScale.Crop,
        alpha = DefaultAlpha,
        painter = painterResource(R.drawable.onboarding_image), contentDescription = ""
    )
}

@Composable
fun OnBoardingText(modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(id = R.string.discover_your),
            style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            text = stringResource(id = R.string.own_dream),
            style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            modifier = Modifier
                .paddingFromBaseline(top = 32.dp, bottom = 32.dp)
                .padding(horizontal = 12.dp),
            text = stringResource(id = R.string.ondoarding_message),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.caption.copy(fontSize = 14.sp)
        )
    }
}

@Composable
fun Buttons(onSignInClick: () -> Unit, onRegisterClick: () -> Unit, modifier: Modifier = Modifier) {
    val buttonModifier = Modifier
        .width(170.dp)
        .height(55.dp)

    val isSingInChecked = rememberSaveable { mutableStateOf(true) }

    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        SignInButton(
            isSingInChecked.value,
            onClick = {
                isSingInChecked.value = true
                onSignInClick()
            },
            buttonModifier
        )
        RegisterButton(
            !isSingInChecked.value,
            onClick = {
                isSingInChecked.value = false
                onRegisterClick()
            },
            buttonModifier
        )
    }
}

@Composable
fun SignInButton(isChecked: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = StartRoundedButtonShape25,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isChecked) MaterialTheme.colors.primary else MaterialTheme.colors.onPrimary,
            contentColor = if (isChecked) MaterialTheme.colors.onPrimary else MaterialTheme.colors.primary
        ),
        border = BorderStroke(1.dp, MaterialTheme.colors.primaryVariant)
    ) {
        Text(
            text = stringResource(id = R.string.sign_in),
            style = MaterialTheme.typography.subtitle1.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp
            )
        )
    }
}

@Composable
fun RegisterButton(isChecked: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = EndRoundedButtonShape25,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isChecked) MaterialTheme.colors.primary else MaterialTheme.colors.onPrimary,
            contentColor = if (isChecked) MaterialTheme.colors.onPrimary else MaterialTheme.colors.primary
        ),
        border = BorderStroke(1.dp, MaterialTheme.colors.primaryVariant)
    ) {
        Text(
            text = stringResource(id = R.string.register),
            style = MaterialTheme.typography.subtitle1.copy(
                fontWeight = FontWeight.Bold, fontSize = 19.sp
            )
        )
    }
}

@Preview
@Composable
fun OnBoardingScreenPreview() {
    SignScreenTheme {
        OnBoardingScreen({}, {})
    }
}