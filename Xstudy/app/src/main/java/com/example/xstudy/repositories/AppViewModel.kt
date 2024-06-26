package com.example.xstudy.repositories

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

// AppViewModel is responsible for managing app-wide state
class AppViewModel : ViewModel() {

    // MutableStateFlow for authentication status
    // Private to prevent direct modification from outside the ViewModel
    private val _authenticated = MutableStateFlow(false)

    // Public read-only StateFlow for observing authentication status
    val authenticated = _authenticated.asStateFlow()

    // MutableStateFlow for login screen visibility
    // Private to prevent direct modification from outside the ViewModel
    private val _isLogin = MutableStateFlow(true)

    // Public read-only StateFlow for observing login screen visibility
    val isLogin = _isLogin.asStateFlow()

    // MutableStateFlow for loading state
    // Private to prevent direct modification from outside the ViewModel
    private val _isLoading = MutableStateFlow(true)
    // Public read-only StateFlow for observing loading state
    val isLoading = _isLoading.asStateFlow()

    private val _isGuest = MutableStateFlow(true)

    val isGuest = _isGuest.asStateFlow()

    private val _isReturningUser = MutableStateFlow(true)

    val isReturningUser = _isReturningUser.asStateFlow()

    private val _isLogout = MutableStateFlow(true)

    val isLogout = _isLogout.asStateFlow()

    private val _isUsername = MutableStateFlow("")

    val isUsername = _isUsername.asStateFlow()

    // Function to update authentication status
    fun setAuthenticated(value: Boolean) {
        _authenticated.value = value
    }

    // Function to update login screen visibility
    fun setIsLogin(value: Boolean) {
        _isLogin.value = value
    }

    // Function to update loading state
    fun setIsLoading(value: Boolean) {
        _isLoading.value = value
    }

    // Function to update guest screen visibility
    fun setIsGuest(value: Boolean) {
        _isGuest.value = value
    }

    // Function to update returning user screen visibility
    fun setIsReturningUser(value: Boolean) {
        _isReturningUser.value = value
    }

    // Function to update logout screen visibility
    fun setIsLogout(value: Boolean) {
        _isLogout.value = value
    }

    // Function to update username screen visibility
    fun setIsUsername(value: String) {
        _isUsername.value = value
    }
}