package com.breaktime.signscreen.screen.profile.personalAccount

import com.breaktime.signscreen.screen.base.BaseViewModel
import com.breaktime.signscreen.screen.profile.personalAccount.PersonalAccountContract.*

class PersonalAccountViewModel :
    BaseViewModel<PersonalAccountEvent, PersonalAccountState, PersonalAccountEffect>() {

    private fun signOut() {
        // TODO implement logic
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

}