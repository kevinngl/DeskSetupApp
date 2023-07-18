package com.kevin.desksetupapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kevin.desksetupapp.data.DeskSetupRepository
import com.kevin.desksetupapp.data.DeskSetupList
import com.kevin.desksetupapp.ui.navigation.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: DeskSetupRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<DeskSetupList>>> =
            MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<DeskSetupList>>>
        get() = _uiState

    fun getAllData() {
        viewModelScope.launch {
            repository
                    .getAllData()
                    .catch { _uiState.value = UiState.Error(it.message.toString()) }
                    .collect { orderRewards -> _uiState.value = UiState.Success(orderRewards) }
        }
    }
}
