package com.lukesmoljak.realestate.framework.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
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


            else -> super.instantiate(classLoader, className)
        }

}
