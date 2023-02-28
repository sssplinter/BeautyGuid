package com.breaktime.signscreen.screen.portfolio

import com.breaktime.signscreen.screen.base.BaseViewModel
import com.breaktime.signscreen.screen.portfolio.SalonPortfolioContract.SalonPortfolioEffect

class SalonPortfolioViewModel :
    BaseViewModel<SalonPortfolioContract.SalonPortfolioEvent, SalonPortfolioContract.SalonPortfolioState, SalonPortfolioEffect>() {

    override fun createInitialState(): SalonPortfolioContract.SalonPortfolioState {
        return SalonPortfolioContract.SalonPortfolioState.Default
    }

    override fun handleEvent(event: SalonPortfolioContract.SalonPortfolioEvent) {
        when (event) {
            SalonPortfolioContract.SalonPortfolioEvent.OnNavigateBackClick -> {
                setEffect { SalonPortfolioEffect.NavigateBack }
            }
            is SalonPortfolioContract.SalonPortfolioEvent.OnPhotoClick -> {
                setEffect { SalonPortfolioEffect.OpenPhoto(event.photoId) }
            }
            is SalonPortfolioContract.SalonPortfolioEvent.OnStoryClick -> {}
            is SalonPortfolioContract.SalonPortfolioEvent.OnSwitchTabClick -> {

            }
        }
    }
}
