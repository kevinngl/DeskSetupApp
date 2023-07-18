package com.kevin.desksetupapp.ui.screen.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kevin.desksetupapp.data.Injection
import com.kevin.desksetupapp.ui.navigation.UiState
import com.kevin.desksetupapp.ui.screen.ViewModelFactory
import com.kevin.desksetupapp.ui.theme.SubmissionApplicationTheme

@Composable
fun DetailScreen(
        id: Long,
        viewModel: DetailViewModel =
                androidx.lifecycle.viewmodel.compose.viewModel(
                        factory = ViewModelFactory(Injection.provideRepository())
                ),
        navigateBack: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let {
        when (it) {
            is UiState.Loading -> {
                viewModel.getById(id)
            }
            is UiState.Success -> {
                val data = it.data
                DetailSetup(
                        DeskSetup_name = data.DeskSetup.DeskSetup_name,
                        DeskSetup_description = data.DeskSetup.DeskSetup_description,
                        DeskSetup_category = data.DeskSetup.DeskSetup_category,
                        DeskSetup_picture = data.DeskSetup.DeskSetup_picture,
                        DeskSetup_date = data.DeskSetup.DeskSetup_date,
                        onBackClick = navigateBack
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailSetup(
        DeskSetup_name: String,
        DeskSetup_description: String,
        DeskSetup_category: String,
        DeskSetup_picture: String,
        DeskSetup_date: String,
        onBackClick: () -> Unit,
        modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.fillMaxSize().padding(16.dp)) {
        item {
            Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.padding(16.dp).clickable { onBackClick() }
            )
            AsyncImage(
                    model = DeskSetup_picture,
                    contentDescription = "DeskSetup_image",
                    contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = DeskSetup_name, fontWeight = FontWeight.Bold, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = DeskSetup_description, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "category : ${DeskSetup_category}")
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "date : ${DeskSetup_date}")
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailSetupPreview() {
    SubmissionApplicationTheme {
        DetailSetup(
                DeskSetup_name = "DeskSetup name",
                DeskSetup_description = "DeskSetup description",
                DeskSetup_category = "DeskSetup category",
                DeskSetup_picture = "DeskSetup picture",
                DeskSetup_date = "DeskSetup date",
                onBackClick = {}
        )
    }
}
