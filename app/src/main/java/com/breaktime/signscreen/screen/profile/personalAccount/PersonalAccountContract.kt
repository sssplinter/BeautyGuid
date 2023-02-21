package com.breaktime.signscreen.screen.profile.personalAccount

import com.breaktime.signscreen.screen.base.UiEffect
import com.breaktime.signscreen.screen.base.UiEvent
import com.breaktime.signscreen.screen.base.UiState

class PersonalAccountContract {
    sealed class PersonalAccountState : UiState {
        object Default : PersonalAccountState()
        object Loading : PersonalAccountState()
    }

    sealed class PersonalAccountEvent : UiEvent {
        object OnSignOut : PersonalAccountEvent()
        object OnEditPersonalData : PersonalAccountEvent()
        object OnOpenNotifications : PersonalAccountEvent()
        object OnOpenAppointments : PersonalAccountEvent()
        object OnOpenSalons : PersonalAccountEvent()
        object OnOpenMasters : PersonalAccountEvent()
    }

    sealed class PersonalAccountEffect : UiEffect {
        data class ShowErrorMessage(val errorMsg: String) : PersonalAccountEffect()
        object SignOut : PersonalAccountEffect()
        object EditPersonalData : PersonalAccountEffect()
        object OpenNotifications : PersonalAccountEffect()
        object OpenAppointments : PersonalAccountEffect()
        object OpenSalons : PersonalAccountEffect()
        object OpenMasters : PersonalAccountEffect()
    }
}