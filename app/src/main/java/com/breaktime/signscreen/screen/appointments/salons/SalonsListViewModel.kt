package com.breaktime.signscreen.screen.appointments.salons

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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

    var salonsList = mutableListOf<SalonPreviewResponse>()
    val salons = mutableStateListOf<SalonPreviewResponse>()

    var selectedCategory by mutableStateOf("")

    var searchValue by mutableStateOf("")

    init {
        viewModelScope.launch {
            setState { SalonsListContract.SalonsListState.Loading }
            salonsList.addAll(getAllSalonsUseCase())
            salons.addAll(salonsList)
            setState { SalonsListContract.SalonsListState.Default }
        }
    }

    fun onSearchValueChange(value: String = "") {
        searchValue = value
        salons.clear()
        if (value.isEmpty()) {
            if (selectedCategory.isNotEmpty()) {
                val selected =
                    salonsList.filter {
                        it.categories.contains(
                            selectedCategory
                        )
                    }
                salons.addAll(selected)
            } else {
                salons.addAll(salonsList)

            }
        } else {
            val selected =
                salonsList.filter {
                    (it.salonName.uppercase()
                        .startsWith(value.uppercase()) || it.salonDescription.uppercase()
                        .contains(value.uppercase())) && if (selectedCategory.isNotEmpty()) it.categories.contains(
                        selectedCategory
                    ) else true
                }
            salons.addAll(selected)
        }
    }

    fun setCategory(category: String) {
        selectedCategory = category
        salons.clear()
        if (selectedCategory.isEmpty()) {
            if (searchValue.isNotEmpty()) {
                val selected =
                    salonsList.filter {
                        (it.salonName.uppercase()
                            .startsWith(searchValue.uppercase()) || it.salonDescription.uppercase()
                            .contains(searchValue.uppercase()))
                    }
                salons.addAll(selected)
            } else {
                salons.addAll(salonsList)
            }
        } else {
            val selected = salonsList.filter {
                it.categories.contains(category) && if (searchValue.isNotEmpty()) (it.salonName.uppercase()
                    .startsWith(searchValue.uppercase()) || it.salonDescription.uppercase()
                    .contains(searchValue.uppercase())) else true
            }
            salons.addAll(selected)
        }
    }

    override fun createInitialState(): SalonsListContract.SalonsListState {
        return SalonsListContract.SalonsListState.Default
    }

    override fun handleEvent(event: SalonsListContract.SalonsListEvent) {
        when (event) {
            SalonsListContract.SalonsListEvent.OnNavigateBackClick -> {
                setEffect { SalonsListContract.SalonsListEffect.NavigateBack }
            }
            is SalonsListContract.SalonsListEvent.OnOpenSalonPortfolio -> {
                setEffect { SalonsListContract.SalonsListEffect.OpenSalonPortfolio(event.salonId) }
            }
        }
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