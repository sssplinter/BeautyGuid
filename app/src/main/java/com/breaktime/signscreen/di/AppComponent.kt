package com.breaktime.signscreen.di

import android.content.Context
import com.breaktime.signscreen.data.network.models.RetrofitHelper
import com.breaktime.signscreen.data.pref.SharedPreferenceRepository
import com.breaktime.signscreen.data.source.authorizationApi.AuthorizationDataSource
import com.breaktime.signscreen.data.source.authorizationApi.AuthorizationRepository
import com.breaktime.signscreen.data.source.authorizationApi.AuthorizationRepositoryImpl
import com.breaktime.signscreen.data.source.authorizationApi.remote.AuthorizationService
import com.breaktime.signscreen.data.source.authorizationApi.remote.RemoteAuthorizationDataSource
import com.breaktime.signscreen.domain.authorization.LoginUseCase
import com.breaktime.signscreen.domain.authorization.RegistrationUseCase
import com.breaktime.signscreen.domain.pref.SetIsAuthorizedUseCase
import com.breaktime.signscreen.screen.authorization.login.LoginViewModel
import com.breaktime.signscreen.screen.authorization.registeration.RegistrationViewModel
import com.breaktime.signscreen.screen.profile.personalAccount.PersonalAccountViewModel
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [AppModule::class])
interface AppComponent {

    fun loginViewModelFactory(): LoginViewModel.Factory
    fun registrationViewModelFactory(): RegistrationViewModel.Factory
    fun personalAccountViewModelFactory(): PersonalAccountViewModel.Factory

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
        setIsAuthorizedUseCase: SetIsAuthorizedUseCase
    ): LoginViewModel.Factory {
        return LoginViewModel.Factory(loginUseCase, setIsAuthorizedUseCase)
    }

    @Provides
    fun provideRegistrationViewModelFactory(
        registrationUseCase: RegistrationUseCase,
        setIsAuthorizedUseCase: SetIsAuthorizedUseCase
    ): RegistrationViewModel.Factory {
        return RegistrationViewModel.Factory(registrationUseCase, setIsAuthorizedUseCase)
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
    fun provideAuthorizationRepository(remoteAuthorizationDataSource: AuthorizationDataSource): AuthorizationRepository {
        return AuthorizationRepositoryImpl(remoteAuthorizationDataSource)
    }

    @Provides
    fun provideSharedPreferencesRepository(context: Context): SharedPreferenceRepository {
        return SharedPreferenceRepository(context)
    }

    @Provides
    fun provideAuthorizationDataSource(authorizationApi: AuthorizationService): AuthorizationDataSource {
        return RemoteAuthorizationDataSource(authorizationApi)
    }

    @Provides
    fun provideAuthorizationApi(): AuthorizationService {
        return RetrofitHelper.getInstance().create(
            AuthorizationService::
            class.java
        )
    }
}