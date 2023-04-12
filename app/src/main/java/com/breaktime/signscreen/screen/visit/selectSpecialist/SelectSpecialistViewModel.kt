package com.breaktime.signscreen.screen.visit.selectSpecialist

import com.breaktime.signscreen.screen.base.BaseViewModel

class SelectSpecialistViewModel :
    BaseViewModel<SelectSpecialistContract.SelectSpecialistEvent, SelectSpecialistContract.SelectSpecialistState, SelectSpecialistContract.SelectSpecialistEffect>() {
    override fun createInitialState(): SelectSpecialistContract.SelectSpecialistState {
        return SelectSpecialistContract.SelectSpecialistState.Default
    }

    override fun handleEvent(event: SelectSpecialistContract.SelectSpecialistEvent) {
//        when (event) {
//            is SelectSpecialistContract.SelectSpecialistEvent.OnMoreInfoClick -> TODO()
//            SelectSpecialistContract.SelectSpecialistEvent.OnNavigateBackClick -> TODO()
//            is SelectSpecialistContract.SelectSpecialistEvent.OnSelectSpecialistClick -> TODO()
//            is SelectSpecialistContract.SelectSpecialistEffect.OpenMoreInfo -> TODO()
//        }
    }
}