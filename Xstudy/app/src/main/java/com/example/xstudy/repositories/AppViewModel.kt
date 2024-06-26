package com.example.xstudy.repositories

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppViewModel : ViewModel() {
    private val _authenticated = MutableStateFlow(false)
    val authenticated = _authenticated.asStateFlow()

    private val _isLogin = MutableStateFlow(true)
    val isLogin = _isLogin.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    fun setAuthenticated(value: Boolean) {
        _authenticated.value = value
    }

    fun setIsLogin(value: Boolean) {
        _isLogin.value = value
    }

    fun setIsLoading(value: Boolean) {
        _isLoading.value = value
    }
}