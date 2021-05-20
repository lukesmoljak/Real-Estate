package com.lukesmoljak.realestate.di

import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.lukesmoljak.realestate.di.scopes.AppScope
import com.lukesmoljak.realestate.framework.presentation.MyFragmentFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@Module
object FragmentFactoryModule {

    @AppScope
    @Provides
    fun providesMyFragmentFactory(
        viewModelFactory: ViewModelProvider.Factory
    ): FragmentFactory {
        return MyFragmentFactory(viewModelFactory = viewModelFactory)
    }
}