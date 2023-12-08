package com.mikirinkode.smartparking.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mikirinkode.smartparking.R
import com.mikirinkode.smartparking.ui.comp.LoadingIndicator
import com.mikirinkode.smartparking.ui.utils.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    val viewModel: HomeViewModel = viewModel()

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

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Box() {
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White),
                    title = {
                        Text(text = "Area Parkir", fontSize = 24.sp)
                    })
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
                                        text = "Masuk ->",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Medium,
                                        modifier = Modifier.padding(16.dp)
                                    )
                                    Row(
                                        modifier = Modifier.fillMaxWidth(1f)
                                    ) {
                                        LazyColumn(
                                            modifier = Modifier.weight(weight = 2f, fill = true)
                                        ) {
                                            item {
                                                Box(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .height(20.dp)
                                                        .border(
                                                            2.dp,
                                                            color = Color.Gray,
                                                            shape = RoundedCornerShape(topEndPercent = 100)
                                                        )
                                                )
                                            }
                                            items(parkingList) { parking ->
                                                val isAvailable =
                                                    (parking.distance > 6.0)
                                                ParkingItemCard(
                                                    parkCode = parking.parkCode,
                                                    isAvailable = isAvailable
                                                )
                                            }
                                            items(dummyParkingAList) { parking ->
                                                ParkingItemCard(
                                                    parkCode = parking.parkCode,
                                                    isAvailable = false
                                                )
                                            }
                                            item {
                                                Box(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .height(20.dp)
                                                        .border(
                                                            2.dp,
                                                            color = Color.Gray,
                                                            shape = RoundedCornerShape(
                                                                bottomEndPercent = 100
                                                            )
                                                        )
                                                )
                                            }
                                        }
                                        Spacer(
                                            modifier = Modifier.weight(weight = 1f, fill = true)
                                        )
                                        LazyColumn(
                                            modifier = Modifier.weight(weight = 2f, fill = true)
                                        ) {
                                            item {
                                                Box(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .height(20.dp)
                                                        .border(
                                                            2.dp,
                                                            color = Color.Gray,
                                                            shape = RoundedCornerShape(topEndPercent = 100)
                                                        )
                                                )
                                            }
                                            items(dummyParkingBList) { parking ->
                                                ParkingItemCard(
                                                    parkCode = parking.parkCode,
                                                    isAvailable = false
                                                )
                                            }
                                            item {
                                                Box(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .height(20.dp)
                                                        .border(
                                                            2.dp,
                                                            color = Color.Gray,
                                                            shape = RoundedCornerShape(
                                                                bottomEndPercent = 100
                                                            )
                                                        )
                                                )
                                            }
                                        }
                                        Spacer(
                                            modifier = Modifier.weight(weight = 1f, fill = true)
                                        )
                                    }
                                    Text(
                                        text = "<- Keluar",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Medium,
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
fun ParkingItemCard(
    parkCode: String,
    isAvailable: Boolean,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .height(70.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(color = Color.Gray)
                .align(Alignment.BottomStart)
        )
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(2.dp)
                .background(color = Color.Gray)
                .align(Alignment.BottomStart)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 16.dp)
                .fillMaxWidth()
                .align(
                    if (isAvailable) {
                        Alignment.Center
                    } else {
                        Alignment.CenterStart
                    }
                )
        ) {
            Text(
                text = parkCode,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = if (isAvailable) {
                    MaterialTheme.colorScheme.primary
                } else {
                    Color.Gray
                }
            )
            Spacer(modifier = Modifier.width(12.dp))
            if (!isAvailable) {
                Image(
                    painter = painterResource(id = R.drawable.ic_white_car),
                    contentDescription = "Car Illustration",
                    modifier = Modifier.size(60.dp)
                )
            }
        }
    }
}