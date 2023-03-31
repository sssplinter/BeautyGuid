package com.breaktime.signscreen.screen.profile.personalAccount

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.breaktime.signscreen.data.network.models.UserRequestInfo
import com.breaktime.signscreen.domain.pref.SetIsAuthorizedUseCase
import com.breaktime.signscreen.domain.user.GetUserPersonalDataUseCase
import com.breaktime.signscreen.screen.base.BaseViewModel
import com.breaktime.signscreen.screen.profile.personalAccount.PersonalAccountContract.*
import kotlinx.coroutines.launch

// TODO add log out use case
class PersonalAccountViewModel(
    private val setIsAuthorizedUseCase: SetIsAuthorizedUseCase,
    private val getUserPersonalDataUseCase: GetUserPersonalDataUseCase
) :
    BaseViewModel<PersonalAccountEvent, PersonalAccountState, PersonalAccountEffect>() {

    val userData = mutableStateOf<UserRequestInfo?>(null)

    init {
        viewModelScope.launch {
            userData.value = getUserPersonalDataUseCase()
        }
    }

    private fun signOut() {
        // TODO implement logic
        setIsAuthorizedUseCase.invoke(false)
        setEffect { PersonalAccountEffect.SignOut }
    }

    override fun createInitialState(): PersonalAccountState {
        return PersonalAccountState.Default
    }

    override fun handleEvent(event: PersonalAccountEvent) {
        when (event) {
            PersonalAccountEvent.OnEditPersonalData -> {
                setEffect { PersonalAccountEffect.EditPersonalData }
            }
            PersonalAccountEvent.OnOpenNotifications -> {
                setEffect { PersonalAccountEffect.OpenNotifications }
            }
            PersonalAccountEvent.OnOpenAppointments -> {
                setEffect { PersonalAccountEffect.OpenAppointments }
            }
            PersonalAccountEvent.OnOpenSalons -> {
                setEffect { PersonalAccountEffect.OpenSalons }
            }
            PersonalAccountEvent.OnOpenMasters -> {
                setEffect { PersonalAccountEffect.OpenMasters }
            }
            PersonalAccountEvent.OnSignOut -> {
                signOut()
            }
            else -> {}
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val setIsAuthorizedUseCase: SetIsAuthorizedUseCase,
        private val getUserPersonalDataUseCase: GetUserPersonalDataUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PersonalAccountViewModel(setIsAuthorizedUseCase, getUserPersonalDataUseCase) as T
        }
    }
}