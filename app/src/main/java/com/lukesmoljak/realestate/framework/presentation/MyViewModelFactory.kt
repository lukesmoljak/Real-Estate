package com.lukesmoljak.realestate.framework.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lukesmoljak.realestate.business.interactors.GetPropertyListingsUseCase
import com.lukesmoljak.realestate.framework.presentation.property_listings.PropertyListingsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
class MyViewModelFactory
constructor(
    private val getPropertyListingsUseCase: GetPropertyListingsUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {

            PropertyListingsViewModel::class.java -> {
                PropertyListingsViewModel(
                    getPropertyListingsUseCase = getPropertyListingsUseCase
                ) as T
            }

            else -> {
                throw IllegalArgumentException("unknown model class $modelClass")
            }
        }
    }

}