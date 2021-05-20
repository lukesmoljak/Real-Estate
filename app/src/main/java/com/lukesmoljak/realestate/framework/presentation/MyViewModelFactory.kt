package com.lukesmoljak.realestate.framework.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
class MyViewModelFactory
constructor(

): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {

            else -> {
                throw IllegalArgumentException("unknown model class $modelClass")
            }
        }
    }

}