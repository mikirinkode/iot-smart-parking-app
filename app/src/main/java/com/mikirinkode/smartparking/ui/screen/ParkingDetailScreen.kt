package com.mikirinkode.smartparking.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.mikirinkode.smartparking.data.Parking
import com.mikirinkode.smartparking.ui.comp.LoadingIndicator
import com.mikirinkode.smartparking.ui.comp.ParkingSlotItemCard
import com.mikirinkode.smartparking.ui.comp.ParkingSideBar
import com.mikirinkode.smartparking.ui.utils.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParkingDetailScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: ParkingViewModel = viewModel()

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Box() {
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                TopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White),
                    title = {
                        Text(text = "Area Parkir", fontSize = 24.sp)
                    },
                    navigationIcon = {
                        IconButton(onClick = navigateBack) {
                            Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "Back Button")
                        }
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
                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Text(
                                        text = "= Jalur Masuk =",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = Color.Gray,
                                        modifier = Modifier.padding(16.dp),
                                    )
                                    ParkingAreaLayout(
                                        parkingList = parkingList,
                                        modifier = Modifier.fillMaxWidth(1f)
                                    )
                                    Text(
                                        text = "= Jalur Keluar =",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = Color.Gray,
                                        modifier = Modifier.padding(16.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ParkingAreaLayout(
    parkingList: List<Parking>,
    modifier: Modifier = Modifier
) {
    val dummyParkingAList = listOf(
        Parking(parkCode = "A3", distance = 0.0),
        Parking(parkCode = "A4", distance = 0.0),
        Parking(parkCode = "A5", distance = 0.0),
        Parking(parkCode = "A6", distance = 0.0),
        Parking(parkCode = "A7", distance = 0.0),
    )
    val dummyParkingBList = listOf(
        Parking(parkCode = "B1", distance = 0.0),
        Parking(parkCode = "B2", distance = 0.0),
        Parking(parkCode = "B3", distance = 0.0),
        Parking(parkCode = "B4", distance = 0.0),
        Parking(parkCode = "B5", distance = 0.0),
        Parking(parkCode = "B6", distance = 0.0),
        Parking(parkCode = "B7", distance = 0.0),
    )

    Row(
        modifier = modifier
    ) {
        LazyColumn(
            modifier = Modifier.weight(weight = 2f, fill = true)
        ) {
            item {
                ParkingSideBar(topEndPercent = 100)
            }
            items(parkingList) { parking ->
                val isAvailable =
                    (parking.distance > 6.0)
                ParkingSlotItemCard(
                    parkCode = parking.parkCode,
                    isAvailable = isAvailable
                )
            }
            items(dummyParkingAList) { parking ->
                ParkingSlotItemCard(
                    parkCode = parking.parkCode,
                    isAvailable = false
                )
            }
            item {
                ParkingSideBar(bottomEndPercent = 100)
            }
        }
        Spacer(
            modifier = Modifier.weight(weight = 1f, fill = true)
        )
        LazyColumn(
            modifier = Modifier.weight(weight = 2f, fill = true)
        ) {
            item {
                ParkingSideBar(topEndPercent = 100)
            }
            items(dummyParkingBList) { parking ->
                ParkingSlotItemCard(
                    parkCode = parking.parkCode,
                    isAvailable = false
                )
            }
            item {
                ParkingSideBar(bottomEndPercent = 100)
            }
        }
        Spacer(
            modifier = Modifier.weight(weight = 1f, fill = true)
        )
    }
}
