package com.breaktime.signscreen.screen.salonNews

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.breaktime.signscreen.data.entities.SalonNewsInfo
import com.breaktime.signscreen.domain.salon.news.GetSalonNewsUseCase
import com.breaktime.signscreen.screen.base.BaseViewModel
import kotlinx.coroutines.launch

class SalonNewsViewModel(
    private val getSalonNewsUseCase: GetSalonNewsUseCase
) :
    BaseViewModel<SalonNewsContract.SalonNewsEvent, SalonNewsContract.SalonNewsState, SalonNewsContract.SalonNewsEffect>() {

    // TODO
    private var salonId = -1
    var salonNewsList = mutableStateListOf<SalonNewsInfo>()

    fun setSalonId(salonId: Int) {
        this.salonId = salonId
        viewModelScope.launch {
            setState { SalonNewsContract.SalonNewsState.Loading }
            salonNewsList.clear()
            salonNewsList.addAll(getSalonNewsUseCase(salonId))
            setState { SalonNewsContract.SalonNewsState.Default }
        }

    }

    override fun createInitialState(): SalonNewsContract.SalonNewsState {
        return SalonNewsContract.SalonNewsState.Default
    }

    override fun handleEvent(event: SalonNewsContract.SalonNewsEvent) {
        when (event) {
            is SalonNewsContract.SalonNewsEvent.OnNavigateBackClick -> {
                setEffect { SalonNewsContract.SalonNewsEffect.NavigateBack(salonId) }
            }
            is SalonNewsContract.SalonNewsEvent.OnSalonClick -> {
                setEffect { SalonNewsContract.SalonNewsEffect.OpenSalonPortfolio(event.salonId) }
            }
            is SalonNewsContract.SalonNewsEvent.OnSpecialistClick -> {
                setEffect { SalonNewsContract.SalonNewsEffect.OpenSpecialistProfile(event.specialistId) }
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val getSalonNewsUseCase: GetSalonNewsUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SalonNewsViewModel(
                getSalonNewsUseCase
            ) as T
        }
    }

}