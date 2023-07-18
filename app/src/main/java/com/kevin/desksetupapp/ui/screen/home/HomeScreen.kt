package com.kevin.desksetupapp.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.kevin.desksetupapp.data.DeskSetupList
import com.kevin.desksetupapp.data.Injection
import com.kevin.desksetupapp.ui.navigation.UiState
import com.kevin.desksetupapp.ui.screen.ViewModelFactory

@Composable
fun HomeScreen(
        modifier: Modifier = Modifier,
        viewModel: HomeViewModel =
                androidx.lifecycle.viewmodel.compose.viewModel(
                        factory = ViewModelFactory(Injection.provideRepository())
                ),
        navigateToDetail: (Long) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let {
        when (it) {
            is UiState.Loading -> {
                viewModel.getAllData()
            }
            is UiState.Success -> {
                HomeListData(DeskSetupList = it.data, navigateToDetail = navigateToDetail)
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeListData(DeskSetupList: List<DeskSetupList>, navigateToDetail: (Long) -> Unit) {
    Box(modifier = Modifier) {
        LazyColumn {
            items(DeskSetupList) {
                DeskSetupsCatalog(
                        name = it.DeskSetup.DeskSetup_name,
                        description = it.DeskSetup.DeskSetup_description,
                        image = it.DeskSetup.DeskSetup_picture,
                        modifier = Modifier.clickable { navigateToDetail(it.DeskSetup.id) }
                )
            }
        }
    }
}
