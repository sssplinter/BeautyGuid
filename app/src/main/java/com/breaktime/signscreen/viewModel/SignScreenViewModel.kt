package com.breaktime.signscreen.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

sealed class UIState{
    object SignedOut: UIState()
    object InProgress: UIState()
    object Error: UIState()
    object SignIn: UIState()
}

class SignScreenViewModel: ViewModel() {
    private val _uiState = mutableStateOf<UIState>(UIState.SignedOut)
    val uiState: State<UIState>
        get() = _uiState
}