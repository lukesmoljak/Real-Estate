package com.lukesmoljak.realestate.framework.presentation.property_listings.state

sealed class PropertyListingsViewState {

    class Loading(val isLoading: Boolean): PropertyListingsViewState()
    class Error(val message: String): PropertyListingsViewState()
}