package com.lukesmoljak.realestate.framework.presentation.property_listings

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lukesmoljak.realestate.business.domain.model.Property
import com.lukesmoljak.realestate.business.domain.state.DataState
import com.lukesmoljak.realestate.business.interactors.GetPropertyListingsUseCase
import com.lukesmoljak.realestate.framework.presentation.property_listings.PropertyListingsViewModel.PropertyListingsEvent.GetPropertyListingsEvent
import com.lukesmoljak.realestate.util.Constants.TAG
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception

class PropertyListingsViewModel(
    private val getPropertyListingsUseCase: GetPropertyListingsUseCase
): ViewModel() {

    private var _error = MutableLiveData<String>()
    private var _isLoading = MutableLiveData(false)
    private var _propertyListings: MutableLiveData<List<Property>> = MutableLiveData()

    val error: LiveData<String>
        get() = _error
    val isLoading: LiveData<Boolean>
        get() = _isLoading
    val propertyListings: LiveData<List<Property>>
        get() = _propertyListings

    init {
        onTriggerEvent(GetPropertyListingsEvent)
    }

    private fun onTriggerEvent(event: PropertyListingsEvent) {
        when (event) {
            is GetPropertyListingsEvent -> {
                getPropertyListingData()
            }
        }
    }

    private fun getPropertyListingData() {
        viewModelScope.launch {
            try {
                getPropertyListingsUseCase.getPropertyListings().collect { dataState ->
                    dataState.loading.let { isLoading ->
                        _isLoading.value = isLoading
                    }
                    dataState.data?.let { data ->
                        _propertyListings.value = data
                    }
                    dataState.error?.let { errorMessage ->
                        _error.value = errorMessage
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "getPropertyListingData Exception caught: ${e.cause}")
                e.printStackTrace()
            }
        }
    }

    sealed class PropertyListingsEvent {
        object GetPropertyListingsEvent: PropertyListingsEvent()
    }

}