package com.mikirinkode.smartparking.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mikirinkode.smartparking.ui.comp.BuildingItemCard
import com.mikirinkode.smartparking.ui.comp.LoadingIndicator
import com.mikirinkode.smartparking.ui.utils.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParkingListScreen(
    onItemClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: ParkingViewModel = viewModel()

    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
        ) {
            TopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White),
                title = {
                    Text(text = "Daftar Tempat Parkir", fontSize = 24.sp)
                }
            )

            Box {
                viewModel.homeState.collectAsState(UiState.Initial).value.let { state ->
                    when (state) {
                        is UiState.Initial -> {
                            viewModel.getData()
                        }

                        is UiState.Loading -> {
                            Box(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                LoadingIndicator(modifier = Modifier.align(Alignment.Center))
                            }
                        }

                        is UiState.Error -> {}
                        is UiState.Success -> {
                            val parkingList = state.data
                            val availableSlot = viewModel.getAvailableSlot(parkingList)
                            Column {
                                BuildingItemCard(
                                    parkingName = "Lampung Center Park",
                                    availableSlot = availableSlot,
                                    maxSlot = 14,
                                    onItemClicked = onItemClicked
                                )
                                BuildingItemCard(
                                    parkingName = "Unila Parking",
                                    availableSlot = 0,
                                    maxSlot = 8,
                                    onItemClicked = {})
                                BuildingItemCard(
                                    parkingName = "ABC Parking Site",
                                    availableSlot = 0,
                                    maxSlot = 8,
                                    onItemClicked = {})
                            }
                        }
                    }
                }
            }
        }
    }
}