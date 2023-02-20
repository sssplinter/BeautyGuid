package com.breaktime.signscreen.screen.profile.common

import com.breaktime.signscreen.screen.base.UiEffect
import com.breaktime.signscreen.screen.base.UiEvent
import com.breaktime.signscreen.screen.base.UiState

class ProfileContract {
    sealed class ProfileState : UiState {
        object Default : ProfileState()
        object Loading : ProfileState()
    }

    sealed class ProfileEvent : UiEvent {
        object OnBackClick : ProfileEvent()
        object OnSaveClick : ProfileEvent()
        object OnChoosePhotoClick : ProfileEvent()
    }

    sealed class ProfileEffect : UiEffect {
        data class ShowErrorMessage(val errorMsg: String) : ProfileEffect()
        object SuccessfulEdit: ProfileEffect()
        object NavigateBack : ProfileEffect()
        object ChoosePhoto : ProfileEffect()
    }
}