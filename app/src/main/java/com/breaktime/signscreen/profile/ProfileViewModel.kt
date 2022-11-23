package com.breaktime.signscreen.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.breaktime.signscreen.data.entities.UserInfo

class ProfileViewModel(
    userId: String?,
    userRole: String?,
    // TODO implement data layer
//    private val userInfoRepository: UserInfoRepository
) : ViewModel() {


    // Fetch the relevant user information from the data layer,
    // ie. userInfoRepository, based on the passed userId argument
    val userInfo by mutableStateOf(UserInfo(userId, userRole,"Kristina $userId"))
    //userInfoRepository.getUserInfo(userId)

}
