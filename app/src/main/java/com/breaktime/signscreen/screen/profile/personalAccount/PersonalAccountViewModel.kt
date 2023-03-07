package com.breaktime.signscreen.screen.profile.personalAccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.breaktime.signscreen.domain.pref.SetIsAuthorizedUseCase
import com.breaktime.signscreen.screen.base.BaseViewModel
import com.breaktime.signscreen.screen.profile.personalAccount.PersonalAccountContract.*

// TODO add log out use case
class PersonalAccountViewModel(
    private val setIsAuthorizedUseCase: SetIsAuthorizedUseCase
) :
    BaseViewModel<PersonalAccountEvent, PersonalAccountState, PersonalAccountEffect>() {

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
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val setIsAuthorizedUseCase: SetIsAuthorizedUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PersonalAccountViewModel(setIsAuthorizedUseCase) as T
        }
    }
}