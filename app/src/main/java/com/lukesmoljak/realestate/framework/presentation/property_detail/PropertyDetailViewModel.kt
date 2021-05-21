package com.lukesmoljak.realestate.framework.presentation.property_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PropertyDetailViewModel: ViewModel() {

    private val _id = MutableLiveData<Int>()
    val id: LiveData<Int>
        get() = _id

    fun setId(id: Int) {
        _id.value = id
    }
}