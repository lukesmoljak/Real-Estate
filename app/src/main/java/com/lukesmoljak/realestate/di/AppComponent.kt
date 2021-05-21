package com.lukesmoljak.realestate.di

import com.lukesmoljak.realestate.MainActivity
import com.lukesmoljak.realestate.di.scopes.AppScope
import com.lukesmoljak.realestate.framework.BaseApplication
import com.lukesmoljak.realestate.framework.presentation.property_detail.PropertyDetailFragment
import com.lukesmoljak.realestate.framework.presentation.property_listings.PropertyListingsFragment
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
@AppScope
@Component(
    modules = [
        AppModule::class,
        FragmentFactoryModule::class,
        ViewModelFactoryModule::class,
        ProductionModule::class,
        BuildVariantModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: BaseApplication): Builder

        fun build(): AppComponent
    }

    fun inject(mainActivity: MainActivity)

    fun inject(propertyListingsFragment: PropertyListingsFragment)
    fun inject(propertyDetailFragment: PropertyDetailFragment)
}