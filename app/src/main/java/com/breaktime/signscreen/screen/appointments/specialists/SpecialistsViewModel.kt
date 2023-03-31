package com.breaktime.signscreen.screen.appointments.specialists

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.breaktime.signscreen.data.entities.SpecialistInfo
import com.breaktime.signscreen.data.network.models.toSpecialistPreviewInfo
import com.breaktime.signscreen.domain.specialist.GetAllSpecialistsUseCase
import com.breaktime.signscreen.screen.base.BaseViewModel
import kotlinx.coroutines.launch

class SpecialistsViewModel(
    private val getAllSpecialistsUseCase: GetAllSpecialistsUseCase
) : BaseViewModel<SpecialistsContract.SpecialistsEvent, SpecialistsContract.SpecialistsState, SpecialistsContract.SpecialistsEffect>() {
    val specialists = mutableStateListOf<SpecialistInfo>()

    init {
        viewModelScope.launch {
            val sp = getAllSpecialistsUseCase()
            specialists.addAll(sp.map { it.toSpecialistPreviewInfo() })
        }
    }

    fun selectSpecialists(item: SpecialistInfo) {
//        specialists.map {
//            it.isChecked = if (it.specialistId == item.specialistId) !it.isChecked else false
//        }
    }

    override fun createInitialState(): SpecialistsContract.SpecialistsState {
        return SpecialistsContract.SpecialistsState.Default
    }

    override fun handleEvent(event: SpecialistsContract.SpecialistsEvent) {
        when (event) {
            is SpecialistsContract.SpecialistsEvent.OnBookVisitClick -> {

            }
            SpecialistsContract.SpecialistsEvent.OnNavigateBackClick -> {
                setEffect { SpecialistsContract.SpecialistsEffect.NavigateBack }
            }
            is SpecialistsContract.SpecialistsEvent.OnSalonClick -> {
                setEffect { SpecialistsContract.SpecialistsEffect.OpenSalonPage(event.salonId) }
            }
            is SpecialistsContract.SpecialistsEvent.OnSpecialistInfoClick -> {

            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val getAllSpecialistsUseCase: GetAllSpecialistsUseCase

    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SpecialistsViewModel(getAllSpecialistsUseCase) as T
        }
    }
}
