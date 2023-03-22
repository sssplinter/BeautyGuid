package com.breaktime.signscreen.screen.profile.personalData

import android.net.Uri
import android.telephony.PhoneNumberUtils
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.breaktime.signscreen.data.entities.Country
import com.breaktime.signscreen.data.network.models.UserRequestInfo
import com.breaktime.signscreen.domain.user.GetUserPersonalDataUseCase
import com.breaktime.signscreen.domain.user.UpdateUserPersonalDataUseCase
import com.breaktime.signscreen.screen.base.BaseViewModel
import com.breaktime.signscreen.screen.profile.personalData.PersonalDataContract.*
import com.breaktime.signscreen.utils.isValidEmailCheck
import kotlinx.coroutines.launch

class EditPersonalDataViewModel(
    private val updateUserPersonalDataUseCase: UpdateUserPersonalDataUseCase,
    private val getUserPersonalDataUseCase: GetUserPersonalDataUseCase
) :
    BaseViewModel<ProfileEvent, ProfileState, ProfileEffect>() {
    // TODO

//    private val builder = Uri.Builder()
//    private val myUrl: Uri = builder.build()

    private val myUrl: Uri = Uri.parse("android.resource://com.breaktime.signscreen/drawable/avata")

    var avatarUri by mutableStateOf(myUrl)

    var surname by mutableStateOf("")
    var name by mutableStateOf("")
    var mobileNumber by mutableStateOf("")
    var email by mutableStateOf("")

    var isValidSurname by mutableStateOf(true)
    var isValidName by mutableStateOf(true)
    var isValidMobileNumber by mutableStateOf(true)
    var isValidEmail by mutableStateOf(true)

    init {
        viewModelScope.launch {
            val userInfo = getUserPersonalDataUseCase()
            surname = userInfo?.lastName ?: ""
            name = userInfo?.firstName ?: ""
            mobileNumber = userInfo?.mobileNumber ?: ""
            email = userInfo?.email ?: ""
        }
    }

    fun onImageChange(value: Uri?) {
        value?.let {
            avatarUri = value
        }
    }

    fun onSurnameValueChange(value: String) {
        surname = value
        isValidSurname = surname.isNotBlank()
    }

    fun onNameValueChange(value: String) {
        name = value
        isValidName = name.isNotBlank()
    }

    fun onEmailValueChange(value: String) {
        email = value
        isValidEmail = email.isNotBlank()
    }

    // TODO move check logic to UTILS
    fun onMobileNumberValueChange(value: String) {
        if (PhoneNumberUtils.isGlobalPhoneNumber(value) && value.length + mobileCountry?.numberCode?.length!! < 16) mobileNumber =
            value
        // TODO
        isValidMobileNumber = mobileNumber.isNotBlank()
    }

    val countriesList = mutableListOf(
        Country("1", "Belarus", "+375"), Country("2", "Russia", "+7")
    )

    var mobileCountryCode by mutableStateOf("")
    private var mobileCountry by mutableStateOf<Country?>(
        Country(
            "1", "Belarus", "+375"
        )
    )

    private fun saveProfileData() {
        if (isInputValid()) {
            viewModelScope.launch {
                updateUserPersonalDataUseCase(
                    userRequestInfo = UserRequestInfo(
                        name, surname, mobileNumber, email
                    )
                )
            }

            setEffect { ProfileEffect.SuccessfulEdit }
        }
    }

    private fun isInputValid(): Boolean {
        var isValid = true
        if (surname.isEmpty() || surname.isBlank()) {
            isValid = false
            isValidSurname = false
            setEffect { ProfileEffect.ShowErrorMessage("Input surname") }
        }
        if (name.isEmpty() || name.isBlank()) {
            isValid = false
            isValidName = false
            setEffect { ProfileEffect.ShowErrorMessage("Input name") }
        }
        // TODO add correct mobile number check
        if (mobileNumber.isEmpty() || mobileNumber.isBlank()) {
            isValid = false
            isValidMobileNumber = false
            setEffect { ProfileEffect.ShowErrorMessage("Input mobile number") }
        }
        if (email.isEmpty() || email.isBlank()) {
            isValid = false
            isValidEmail = false
            setEffect { ProfileEffect.ShowErrorMessage("Input email") }
        } else if (!isValidEmailCheck(email)) {
            isValid = false
            isValidEmail = false
            setEffect { ProfileEffect.ShowErrorMessage("Incorrect email format") }
        }
        return isValid
    }

    override fun createInitialState(): ProfileState {
        return ProfileState.Default
    }

    override fun handleEvent(event: ProfileEvent) {
        when (event) {
            ProfileEvent.OnBackClick -> {
                setEffect { ProfileEffect.NavigateBack }
            }
            ProfileEvent.OnChoosePhotoClick -> {
                setEffect { ProfileEffect.ChoosePhoto }
            }
            ProfileEvent.OnSaveClick -> {
                saveProfileData()
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val updateUserPersonalDataUseCase: UpdateUserPersonalDataUseCase,
        private val getUserPersonalDataUseCase: GetUserPersonalDataUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return EditPersonalDataViewModel(
                updateUserPersonalDataUseCase,
                getUserPersonalDataUseCase
            ) as T
        }
    }
}