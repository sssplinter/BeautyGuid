package com.breaktime.signscreen.screen.appointments.salons

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.breaktime.signscreen.data.source.salonApi.remote.SalonPreviewResponse
import com.breaktime.signscreen.domain.salon.GetAllSalonsUseCase
import com.breaktime.signscreen.screen.base.BaseViewModel
import kotlinx.coroutines.launch

class SalonsListViewModel(
    private val getAllSalonsUseCase: GetAllSalonsUseCase
) :
    BaseViewModel<SalonsListContract.SalonsListEvent, SalonsListContract.SalonsListState, SalonsListContract.SalonsListEffect>() {
    val salons = mutableStateListOf<SalonPreviewResponse>()

    init {
        viewModelScope.launch {
            salons.clear()
            salons.addAll(getAllSalonsUseCase())
        }
    }

    override fun createInitialState(): SalonsListContract.SalonsListState {
        return SalonsListContract.SalonsListState.Default
    }

    override fun handleEvent(event: SalonsListContract.SalonsListEvent) {
//        when(event){
//
//        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val getAllSalonsUseCase: GetAllSalonsUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SalonsListViewModel(getAllSalonsUseCase) as T
        }
    }
}