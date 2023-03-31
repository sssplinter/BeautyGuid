package com.breaktime.signscreen.screen.portfolio

import com.breaktime.signscreen.screen.base.UiEffect
import com.breaktime.signscreen.screen.base.UiEvent
import com.breaktime.signscreen.screen.base.UiState

class SalonPortfolioContract {
    sealed class SalonPortfolioState : UiState {
        object Default : SalonPortfolioState()
        object Loading : SalonPortfolioState()
    }

    sealed class SalonPortfolioEvent : UiEvent {
        object OnNavigateBackClick : SalonPortfolioEvent()
        data class OnSwitchTabClick(val tabId: Int) : SalonPortfolioEvent()
        data class OnStoryClick(val storyId: String) : SalonPortfolioEvent()
        data class OnPhotoClick(val photoId: Int) : SalonPortfolioEvent()
    }

    sealed class SalonPortfolioEffect : UiEffect {
        object NavigateBack : SalonPortfolioEffect()
        data class ShowErrorMessage(val errorMsg: String) : SalonPortfolioEffect()
        data class SwitchTab(val tabId: Int) : SalonPortfolioEffect()
        data class OpenStory(val storyId: String) : SalonPortfolioEffect()
        data class OpenPhoto(val photoId: Int, val salonId: Int) : SalonPortfolioEffect()
    }
}