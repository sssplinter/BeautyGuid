package com.breaktime.signscreen.screen.profile.personalData

import android.net.Uri
import android.telephony.PhoneNumberUtils
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.breaktime.signscreen.data.entities.Country
import com.breaktime.signscreen.screen.base.BaseViewModel
import com.breaktime.signscreen.screen.profile.personalData.PersonalDataContract.*
import com.breaktime.signscreen.utils.isValidEmailCheck

class EditPersonalDataViewModel : BaseViewModel<ProfileEvent, ProfileState, ProfileEffect>() {
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
            // TODO invoke use case
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
}