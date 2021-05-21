package com.lukesmoljak.realestate.framework.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.lukesmoljak.realestate.framework.presentation.property_detail.PropertyDetailFragment
import com.lukesmoljak.realestate.framework.presentation.property_listings.PropertyListingsFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
class MyFragmentFactory
@Inject
constructor(
    private val viewModelFactory: ViewModelProvider.Factory
): FragmentFactory(){

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
        when (className) {

            PropertyListingsFragment::class.java.name -> {
                val fragment =
                    PropertyListingsFragment(
                        viewModelFactory
                    )
                fragment
            }

            PropertyDetailFragment::class.java.name -> {
                val fragment =
                        PropertyDetailFragment(
                                viewModelFactory
                        )
                fragment
            }

            else -> super.instantiate(classLoader, className)
        }

}
