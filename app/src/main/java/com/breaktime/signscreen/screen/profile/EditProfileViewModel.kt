package com.breaktime.signscreen.screen.profile

import android.app.DatePickerDialog
import android.content.Context
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.net.Uri
import android.telephony.PhoneNumberUtils
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.breaktime.signscreen.uiItems.inputFields.FormViewModel

class EditProfileViewModel : ViewModel() {
    val builder = Uri.Builder()
    val myUrl = builder.build()


    var avatarUri by mutableStateOf(myUrl)
    var surname by mutableStateOf("")
    var name by mutableStateOf("")
    var patronymic by mutableStateOf("")
    var dateOfBirth by mutableStateOf("")

    var mobileNumber by mutableStateOf("")
    var email by mutableStateOf("")

    var isValidSurname by mutableStateOf(true)
    var isValidName by mutableStateOf(true)
    var isValidPatronymic by mutableStateOf(true)
    var isValidDateOfBirth by mutableStateOf(true)

    var isValidMobileNumber by mutableStateOf(true)
    var isValidEmail by mutableStateOf(true)

    fun onSurnameValueChange(value: String) {
        surname = value
        isValidSurname = surname.isNotBlank()
    }

    fun onNameValueChange(value: String) {
        name = value
        isValidName = name.isNotBlank()
    }

    fun onPatronymicValueChange(value: String) {
        patronymic = value
        isValidPatronymic = patronymic.isNotBlank()
    }

    fun onBirthdayValueChange(value: String) {
        dateOfBirth = value
        // TODO
        isValidDateOfBirth = dateOfBirth.isNotBlank()
    }

    fun onEmailValueChange(value: String) {
        email = value
        isValidEmail = email.isNotBlank()
    }

    // TODO move check logic to UTILS
    fun onMobileNumberValueChange(value: String) {
        if (PhoneNumberUtils.isGlobalPhoneNumber(value) && value.length + mobileCountry?.numberCode?.length!! < 16)
            mobileNumber = value
        // TODO
        isValidMobileNumber = mobileNumber.isNotBlank()
    }

    val countriesList = mutableListOf(
        FormViewModel.Country("1", "Belarus", "+375"),
        FormViewModel.Country("2", "Russia", "+7")
    )

    var mobileCountryCode by mutableStateOf("")
    var mobileCountry by mutableStateOf<FormViewModel.Country?>(
        FormViewModel.Country(
            "2",
            "Russia",
            "+7"
        )
    )

    private var dateFormat = "dd.MM.yyyy"

    fun showDatePickerDialog(context: Context) {
        val calendar = getCalendar()
        DatePickerDialog(
            context, { _, year, month, day ->
                dateOfBirth = getPickedDateAsString(year, month, day)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
            .show()
    }

    private fun getCalendar(): Calendar {
        return if (dateOfBirth.isEmpty())
            Calendar.getInstance()
        else
            getLastPickedDateCalendar()
    }


    private fun getLastPickedDateCalendar(): Calendar {
        val dateFormat = SimpleDateFormat(dateFormat)
        val calendar = Calendar.getInstance()
        calendar.time = dateFormat.parse(dateOfBirth)
        return calendar
    }

    private fun getPickedDateAsString(year: Int, month: Int, day: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val dateFormat = SimpleDateFormat(dateFormat)
        return dateFormat.format(calendar.time)
    }


}