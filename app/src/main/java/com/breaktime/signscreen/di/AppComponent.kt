package com.breaktime.signscreen.di

import android.content.Context
import com.breaktime.signscreen.data.network.models.RetrofitHelper
import com.breaktime.signscreen.data.pref.SharedPreferenceRepository
import com.breaktime.signscreen.data.source.authorizationApi.AuthorizationDataSource
import com.breaktime.signscreen.data.source.authorizationApi.AuthorizationRepository
import com.breaktime.signscreen.data.source.authorizationApi.AuthorizationRepositoryImpl
import com.breaktime.signscreen.data.source.authorizationApi.remote.AuthorizationService
import com.breaktime.signscreen.data.source.authorizationApi.remote.RemoteAuthorizationDataSource
import com.breaktime.signscreen.data.source.salonApi.SalonDataSource
import com.breaktime.signscreen.data.source.salonApi.SalonRepository
import com.breaktime.signscreen.data.source.salonApi.SalonRepositoryImpl
import com.breaktime.signscreen.data.source.salonApi.remote.RemoteSalonDataSource
import com.breaktime.signscreen.data.source.salonApi.remote.SalonService
import com.breaktime.signscreen.data.source.specialistApi.SpecialistDataSource
import com.breaktime.signscreen.data.source.specialistApi.SpecialistRepository
import com.breaktime.signscreen.data.source.specialistApi.SpecialistRepositoryImpl
import com.breaktime.signscreen.data.source.specialistApi.remote.RemoteSpecialistDataSource
import com.breaktime.signscreen.data.source.specialistApi.remote.SpecialistService
import com.breaktime.signscreen.data.source.userDataApi.UserDataDataSource
import com.breaktime.signscreen.data.source.userDataApi.UserDataRepository
import com.breaktime.signscreen.data.source.userDataApi.UserDataRepositoryImpl
import com.breaktime.signscreen.data.source.userDataApi.remote.RemoteUserDataDataSource
import com.breaktime.signscreen.data.source.userDataApi.remote.UserDataService
import com.breaktime.signscreen.domain.authorization.LoginUseCase
import com.breaktime.signscreen.domain.authorization.RegistrationUseCase
import com.breaktime.signscreen.domain.pref.SetIsAuthorizedUseCase
import com.breaktime.signscreen.domain.pref.SetUserTokenUseCase
import com.breaktime.signscreen.domain.salon.GetAllSalonsUseCase
import com.breaktime.signscreen.domain.salon.GetSalonInfoByIdUseCase
import com.breaktime.signscreen.domain.salon.GetSalonPreviewByIdUseCase
import com.breaktime.signscreen.domain.salon.news.GetSalonNewsPreviewsUseCase
import com.breaktime.signscreen.domain.specialist.GetAllSpecialistsUseCase
import com.breaktime.signscreen.domain.user.GetUserPersonalDataUseCase
import com.breaktime.signscreen.domain.user.UpdateUserPersonalDataUseCase
import com.breaktime.signscreen.screen.appointments.salons.SalonsListViewModel
import com.breaktime.signscreen.screen.appointments.specialists.SpecialistsViewModel
import com.breaktime.signscreen.screen.authorization.login.LoginViewModel
import com.breaktime.signscreen.screen.authorization.registeration.RegistrationViewModel
import com.breaktime.signscreen.screen.portfolio.SalonPortfolioViewModel
import com.breaktime.signscreen.screen.profile.personalAccount.PersonalAccountViewModel
import com.breaktime.signscreen.screen.profile.personalData.EditPersonalDataViewModel
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [AppModule::class, UserDataModule::class, SalonModule::class, SpecialistModule::class])
interface AppComponent {

    fun loginViewModelFactory(): LoginViewModel.Factory
    fun registrationViewModelFactory(): RegistrationViewModel.Factory
    fun personalAccountViewModelFactory(): PersonalAccountViewModel.Factory
    fun editPersonalDataViewModelFactory(): EditPersonalDataViewModel.Factory
    fun salonsListViewModel(): SalonsListViewModel.Factory
    fun specialistsListViewModel(): SpecialistsViewModel.Factory
    fun salonPortfolioViewModel(): SalonPortfolioViewModel.Factory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}

@Module
object AppModule {

    @Provides
    fun provideLoginViewModelFactory(
        loginUseCase: LoginUseCase,
        setUserTokenUseCase: SetUserTokenUseCase,
        setIsAuthorizedUseCase: SetIsAuthorizedUseCase
    ): LoginViewModel.Factory {
        return LoginViewModel.Factory(loginUseCase, setUserTokenUseCase, setIsAuthorizedUseCase)
    }

    @Provides
    fun provideRegistrationViewModelFactory(
        registrationUseCase: RegistrationUseCase,
        setUserTokenUseCase: SetUserTokenUseCase,
        setIsAuthorizedUseCase: SetIsAuthorizedUseCase
    ): RegistrationViewModel.Factory {
        return RegistrationViewModel.Factory(
            registrationUseCase,
            setUserTokenUseCase,
            setIsAuthorizedUseCase
        )
    }

    @Provides
    fun providePersonalAccountViewModelFactory(
        setIsAuthorizedUseCase: SetIsAuthorizedUseCase
    ): PersonalAccountViewModel.Factory {
        return PersonalAccountViewModel.Factory(setIsAuthorizedUseCase)
    }

    @Provides
    fun provideLoginUseCase(authorizationRepository: AuthorizationRepository): LoginUseCase {
        return LoginUseCase(authorizationRepository)
    }

    @Provides
    fun provideRegistrationUseCase(authorizationRepository: AuthorizationRepository): RegistrationUseCase {
        return RegistrationUseCase(authorizationRepository)
    }

    @Provides
    fun provideSetIsAuthorizedUseCase(sharedPreferenceRepository: SharedPreferenceRepository): SetIsAuthorizedUseCase {
        return SetIsAuthorizedUseCase(sharedPreferenceRepository)
    }

    @Provides
    fun provideSetUserTokenUseCase(sharedPreferenceRepository: SharedPreferenceRepository): SetUserTokenUseCase {
        return SetUserTokenUseCase(sharedPreferenceRepository)
    }

    @Provides
    fun provideAuthorizationRepository(remoteAuthorizationDataSource: AuthorizationDataSource): AuthorizationRepository {
        return AuthorizationRepositoryImpl(remoteAuthorizationDataSource)
    }

    @Provides
    fun provideAuthorizationDataSource(authorizationApi: AuthorizationService): AuthorizationDataSource {
        return RemoteAuthorizationDataSource(authorizationApi)
    }

    @Provides
    fun provideAuthorizationApi(sharedPreferenceRepository: SharedPreferenceRepository): AuthorizationService {
        return RetrofitHelper.getInstance(sharedPreferenceRepository).create(
            AuthorizationService::
            class.java
        )
    }

    @Provides
    fun provideSharedPreferencesRepository(context: Context): SharedPreferenceRepository {
        return SharedPreferenceRepository(context)
    }
}

@Module
object UserDataModule {
    @Provides
    fun provideEditPersonalDataViewModelFactory(
        updateUserPersonalDataUseCase: UpdateUserPersonalDataUseCase,
        getUserPersonalDataUseCase: GetUserPersonalDataUseCase
    ): EditPersonalDataViewModel.Factory {
        return EditPersonalDataViewModel.Factory(
            updateUserPersonalDataUseCase,
            getUserPersonalDataUseCase
        )
    }

    @Provides
    fun provideUpdateUserPersonalDataUseCase(userDataRepository: UserDataRepository): UpdateUserPersonalDataUseCase {
        return UpdateUserPersonalDataUseCase(userDataRepository)
    }

    @Provides
    fun provideGetUserPersonalDataUseCase(userDataRepository: UserDataRepository): GetUserPersonalDataUseCase {
        return GetUserPersonalDataUseCase(userDataRepository)
    }

    @Provides
    fun provideUserDataRepository(userDataDataSource: UserDataDataSource): UserDataRepository {
        return UserDataRepositoryImpl(userDataDataSource)
    }

    @Provides
    fun provideUserDataDataSource(userDataApi: UserDataService): UserDataDataSource {
        return RemoteUserDataDataSource(userDataApi)
    }

    @Provides
    fun provideUserDataApi(sharedPreferenceRepository: SharedPreferenceRepository): UserDataService {
        return RetrofitHelper.getInstance(sharedPreferenceRepository).create(
            UserDataService::
            class.java
        )
    }
}

@Module
object SalonModule {

    @Provides
    fun provideSalonPortfolioVewModelFactory(
        getSalonPreviewByIdUseCase: GetSalonPreviewByIdUseCase,
        getSalonInfoByIdUSeCase: GetSalonInfoByIdUseCase,
        getSalonNewsPreviewsUseCase: GetSalonNewsPreviewsUseCase
    ): SalonPortfolioViewModel.Factory {
        return SalonPortfolioViewModel.Factory(
            getSalonPreviewByIdUseCase,
            getSalonInfoByIdUSeCase,
            getSalonNewsPreviewsUseCase
        )
    }

    @Provides
    fun provideSalonsListViewModelFactory(
        getAllSalonsUseCase: GetAllSalonsUseCase
    ): SalonsListViewModel.Factory {
        return SalonsListViewModel.Factory(getAllSalonsUseCase)
    }

    @Provides
    fun provideGetAllSalonsUseCase(salonRepository: SalonRepository): GetAllSalonsUseCase {
        return GetAllSalonsUseCase(salonRepository)
    }

    @Provides
    fun provideGetSalonPreviewByIdsUseCase(salonRepository: SalonRepository): GetSalonPreviewByIdUseCase {
        return GetSalonPreviewByIdUseCase(salonRepository)
    }

    @Provides
    fun provideGetSalonNewsPreviewUseCase(salonRepository: SalonRepository): GetSalonNewsPreviewsUseCase {
        return GetSalonNewsPreviewsUseCase(salonRepository)
    }

    @Provides
    fun provideGetSalonInfoByIdUSeCase(salonRepository: SalonRepository): GetSalonInfoByIdUseCase {
        return GetSalonInfoByIdUseCase(salonRepository)
    }

    @Provides
    fun provideSalonRepository(salonDataSource: SalonDataSource): SalonRepository {
        return SalonRepositoryImpl(salonDataSource)
    }

    @Provides
    fun provideSalonDataSource(salonApi: SalonService): SalonDataSource {
        return RemoteSalonDataSource(salonApi)
    }

    @Provides
    fun provideSalonApi(sharedPreferenceRepository: SharedPreferenceRepository): SalonService {
        return RetrofitHelper.getInstance(sharedPreferenceRepository).create(
            SalonService::
            class.java
        )
    }
}

@Module
object SpecialistModule {

    @Provides
    fun provideSpecialistsListViewModelFactory(
        getAllSpecialistsUseCase: GetAllSpecialistsUseCase
    ): SpecialistsViewModel.Factory {
        return SpecialistsViewModel.Factory(getAllSpecialistsUseCase)
    }

    @Provides
    fun provideGetAllSpecialistsUSeCase(specialistRepository: SpecialistRepository): GetAllSpecialistsUseCase {
        return GetAllSpecialistsUseCase(specialistRepository)
    }

    @Provides
    fun provideSpecialistRepository(specialistDataSource: SpecialistDataSource): SpecialistRepository {
        return SpecialistRepositoryImpl(specialistDataSource)
    }

    @Provides
    fun provideSpecialistDataSource(specialistApi: SpecialistService): SpecialistDataSource {
        return RemoteSpecialistDataSource(specialistApi)
    }

    @Provides
    fun provideSpecialistApi(sharedPreferenceRepository: SharedPreferenceRepository): SpecialistService {
        return RetrofitHelper.getInstance(sharedPreferenceRepository).create(
            SpecialistService::
            class.java
        )
    }
}