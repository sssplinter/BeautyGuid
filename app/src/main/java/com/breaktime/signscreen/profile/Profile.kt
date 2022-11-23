import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.breaktime.signscreen.data.entities.UserInfo
import com.breaktime.signscreen.profile.ProfileViewModel
import com.breaktime.signscreen.profile.ProfileViewModelFactory

@Composable
fun TestProfileScreen(userInfo: UserInfo) {
    Text(text = "${userInfo.userId} ${userInfo.userName}")
}

// TODO
@Composable
fun ProfileScreen(
    userId: String?, userRole: String?,
    onButtonClick: () -> Unit, modifier: Modifier = Modifier
) {
    val profileViewModel: ProfileViewModel =
        viewModel(factory = ProfileViewModelFactory(userId, userRole))

    val userInfo = profileViewModel.userInfo

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Profile", style = MaterialTheme.typography.h1)
        Text(text = " userName: ${userInfo.userName}\n userRole: ${userInfo.userRole}")
        Button(onClick = { onButtonClick() }) {
            Text(text = "See portfolio")
        }
    }
}