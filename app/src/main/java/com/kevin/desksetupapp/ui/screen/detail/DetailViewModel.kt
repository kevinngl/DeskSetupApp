package com.kevin.desksetupapp.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kevin.desksetupapp.data.DeskSetupRepository
import com.kevin.desksetupapp.model.DeskSetupList
import com.kevin.desksetupapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: DeskSetupRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<DeskSetupList>> =
            MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<DeskSetupList>>
        get() = _uiState

    fun getById(phoneId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getById(phoneId))
        }
    }
}
