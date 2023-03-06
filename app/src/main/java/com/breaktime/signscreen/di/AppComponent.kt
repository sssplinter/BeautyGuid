package com.breaktime.signscreen.di

import com.breaktime.signscreen.data.network.models.RetrofitHelper
import com.breaktime.signscreen.data.source.authorization.AuthorizationDataSource
import com.breaktime.signscreen.data.source.authorization.AuthorizationRepository
import com.breaktime.signscreen.data.source.authorization.AuthorizationRepositoryImpl
import com.breaktime.signscreen.data.source.authorization.remote.AuthorizationService
import com.breaktime.signscreen.data.source.authorization.remote.RemoteAuthorizationDataSource
import com.breaktime.signscreen.domain.authorization.LoginUseCase
import com.breaktime.signscreen.domain.authorization.RegistrationUseCase
import com.breaktime.signscreen.screen.authorization.login.LoginViewModel
import com.breaktime.signscreen.screen.authorization.registeration.RegistrationViewModel
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [AppModule::class])
interface AppComponent {
    fun loginViewModelFactory(): LoginViewModel.Factory
    fun registrationViewModelFactory(): RegistrationViewModel.Factory
}

@Module
object AppModule {

    @Provides
    fun provideLoginViewModelFactory(loginUseCase: LoginUseCase): LoginViewModel.Factory {
        return LoginViewModel.Factory(loginUseCase)
    }

    @Provides
    fun provideRegistrationViewModelFactory(registrationUseCase: RegistrationUseCase): RegistrationViewModel.Factory {
        return RegistrationViewModel.Factory(registrationUseCase)
    }

    @Provides
    fun provideRegistrationViewModel(registrationUseCase: RegistrationUseCase): RegistrationViewModel {
        return RegistrationViewModel(registrationUseCase)
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
    fun provideAuthorizationRepository(remoteAuthorizationDataSource: AuthorizationDataSource): AuthorizationRepository {
        return AuthorizationRepositoryImpl(remoteAuthorizationDataSource)
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