package com.breaktime.signscreen.screen.portfolio

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.breaktime.signscreen.data.entities.SpecialistInfo
import com.breaktime.signscreen.data.network.models.SalonNewsPreview
import com.breaktime.signscreen.data.network.models.toSalonDetailsInfo
import com.breaktime.signscreen.data.network.models.toSpecialistInfo
import com.breaktime.signscreen.data.source.salonApi.remote.SalonPreviewResponse
import com.breaktime.signscreen.domain.salon.GetSalonInfoByIdUseCase
import com.breaktime.signscreen.domain.salon.GetSalonPreviewByIdUseCase
import com.breaktime.signscreen.domain.salon.news.GetSalonNewsPreviewsUseCase
import com.breaktime.signscreen.domain.specialist.GetSpecialistsBySalonIdUseCase
import com.breaktime.signscreen.screen.base.BaseViewModel
import com.breaktime.signscreen.screen.portfolio.SalonPortfolioContract.SalonPortfolioEffect
import kotlinx.coroutines.launch

class SalonPortfolioViewModel(
    private val getSalonPreviewByIdUseCase: GetSalonPreviewByIdUseCase,
    private val getSalonInfoByIdUseCase: GetSalonInfoByIdUseCase,
    private val getSalonNewsPreviewsUseCase: GetSalonNewsPreviewsUseCase,
    private val getSpecialistsBySalonIdUseCase: GetSpecialistsBySalonIdUseCase
) :
    BaseViewModel<SalonPortfolioContract.SalonPortfolioEvent, SalonPortfolioContract.SalonPortfolioState, SalonPortfolioEffect>() {

    private var salonId: Int = -1
    var salonPreview = mutableStateOf<SalonPreviewResponse?>(null)
    var salonDetails = mutableStateOf<SalonDetailsInfo?>(null)
    var salonNewsPreviews = mutableStateListOf<SalonNewsPreview>()
    var salonSpecialists = mutableStateListOf<SpecialistInfo>()
    override fun createInitialState(): SalonPortfolioContract.SalonPortfolioState {
        return SalonPortfolioContract.SalonPortfolioState.Default
    }

    fun setSalonId(salonId: Int) {
        this.salonId = salonId
        viewModelScope.launch {
            salonPreview.value = getSalonPreviewByIdUseCase(salonId)
            val salonInfo = getSalonInfoByIdUseCase(salonId)
            salonDetails.value = salonInfo?.toSalonDetailsInfo(salonPreview.value?.location)
            salonNewsPreviews.clear()
            salonNewsPreviews.addAll(getSalonNewsPreviewsUseCase(salonId))
            salonSpecialists.clear()
            val specialists = getSpecialistsBySalonIdUseCase(salonId)
            salonSpecialists.addAll(specialists.map { it.toSpecialistInfo() })
        }
    }

    override fun handleEvent(event: SalonPortfolioContract.SalonPortfolioEvent) {
        when (event) {
            SalonPortfolioContract.SalonPortfolioEvent.OnNavigateBackClick -> {
                setEffect { SalonPortfolioEffect.NavigateBack }
            }
            is SalonPortfolioContract.SalonPortfolioEvent.OnPhotoClick -> {
                setEffect { SalonPortfolioEffect.OpenPhoto(event.photoId, salonId) }
            }
            is SalonPortfolioContract.SalonPortfolioEvent.OnStoryClick -> {}
            is SalonPortfolioContract.SalonPortfolioEvent.OnSwitchTabClick -> {

            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val getSalonPreviewByIdUseCase: GetSalonPreviewByIdUseCase,
        private val getSalonInfoByIdUSeCase: GetSalonInfoByIdUseCase,
        private val getSalonNewsPreviewsUseCase: GetSalonNewsPreviewsUseCase,
        private val getSpecialistsBySalonIdUseCase: GetSpecialistsBySalonIdUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SalonPortfolioViewModel(
                getSalonPreviewByIdUseCase,
                getSalonInfoByIdUSeCase,
                getSalonNewsPreviewsUseCase,
                getSpecialistsBySalonIdUseCase
            ) as T
        }
    }

// TODO resolve issue with view model with arguments not from graph injection

//    @Suppress("UNCHECKED_CAST")
//    class Factory @AssistedInject constructor(
//        @Assisted("salonId") private val salonId: Int,
//        private val getSalonInfoByIdUSeCase: GetSalonInfoByIdUseCase
//    ) : ViewModelProvider.Factory {
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            return SalonPortfolioViewModel(salonId, getSalonInfoByIdUSeCase) as T
//        }
//
//        @AssistedFactory
//        interface Factory {
//            fun create(@Assisted("salonId") salonId: Int): SalonsListViewModel.Factory
//        }
//    }
}
