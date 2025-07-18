package com.example.speaksurge

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainMenuViewModel : ViewModel() {
    // Exit dialog state
    private val _showExitDialog = MutableStateFlow(false)
    val showExitDialog: StateFlow<Boolean> = _showExitDialog.asStateFlow()
    
    // Exit dialog actions
    fun showExitDialog() {
        _showExitDialog.value = true
    }
    
    fun hideExitDialog() {
        _showExitDialog.value = false
    }
} 