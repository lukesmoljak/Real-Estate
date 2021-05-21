package com.lukesmoljak.realestate.di

import androidx.lifecycle.ViewModelProvider
import com.lukesmoljak.realestate.business.interactors.GetPropertyListingsUseCase
import com.lukesmoljak.realestate.di.scopes.AppScope
import com.lukesmoljak.realestate.framework.presentation.MyViewModelFactory
import dagger.Module
import dagger.Provides

@Module
object ViewModelFactoryModule {

    @AppScope
    @Provides
    fun providesViewModelFactory(
        getPropertyListingsUseCase: GetPropertyListingsUseCase
    ): ViewModelProvider.Factory {
        return MyViewModelFactory(getPropertyListingsUseCase = getPropertyListingsUseCase)
    }
}