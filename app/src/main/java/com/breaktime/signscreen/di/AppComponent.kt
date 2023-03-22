package com.breaktime.signscreen.di

import android.content.Context
import com.breaktime.signscreen.data.network.models.RetrofitHelper
import com.breaktime.signscreen.data.pref.SharedPreferenceRepository
import com.breaktime.signscreen.data.source.authorizationApi.AuthorizationDataSource
import com.breaktime.signscreen.data.source.authorizationApi.AuthorizationRepository
import com.breaktime.signscreen.data.source.authorizationApi.AuthorizationRepositoryImpl
import com.breaktime.signscreen.data.source.authorizationApi.remote.AuthorizationService
import com.breaktime.signscreen.data.source.authorizationApi.remote.RemoteAuthorizationDataSource
import com.breaktime.signscreen.data.source.mainApi.UserDataDataSource
import com.breaktime.signscreen.data.source.mainApi.UserDataRepository
import com.breaktime.signscreen.data.source.mainApi.UserDataRepositoryImpl
import com.breaktime.signscreen.data.source.mainApi.remote.RemoteUserDataDataSource
import com.breaktime.signscreen.data.source.mainApi.remote.UserDataService
import com.breaktime.signscreen.domain.authorization.LoginUseCase
import com.breaktime.signscreen.domain.authorization.RegistrationUseCase
import com.breaktime.signscreen.domain.pref.SetIsAuthorizedUseCase
import com.breaktime.signscreen.domain.pref.SetUserTokenUseCase
import com.breaktime.signscreen.domain.user.GetUserPersonalDataUseCase
import com.breaktime.signscreen.domain.user.UpdateUserPersonalDataUseCase
import com.breaktime.signscreen.screen.authorization.login.LoginViewModel
import com.breaktime.signscreen.screen.authorization.registeration.RegistrationViewModel
import com.breaktime.signscreen.screen.profile.personalAccount.PersonalAccountViewModel
import com.breaktime.signscreen.screen.profile.personalData.EditPersonalDataViewModel
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [AppModule::class, UserDataModule::class])
interface AppComponent {

    fun loginViewModelFactory(): LoginViewModel.Factory
    fun registrationViewModelFactory(): RegistrationViewModel.Factory
    fun personalAccountViewModelFactory(): PersonalAccountViewModel.Factory
    fun editPersonalDataViewModelFactory(): EditPersonalDataViewModel.Factory

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