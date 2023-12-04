package com.mikirinkode.smartparking.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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

    Surface(
        modifier = modifier.fillMaxSize(),
        color = Color(0xFFf0f7ff)
    ) {
        Box() {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CenterAlignedTopAppBar(title = {
                    Text(text = "Smart Parking", fontSize = 24.sp)
                })
                Box {
                    viewModel.homeState.collectAsState(UiState.Initial).value.let { state ->
                        when (state) {
                            is UiState.Initial -> {
                                viewModel.getData()
                            }

                            is UiState.Loading -> {
                                Box() {
                                    LoadingIndicator(modifier = Modifier.align(Alignment.Center))
                                }
                            }

                            is UiState.Error -> {}
                            is UiState.Success -> {
                                val parkingList = state.data

                                LazyVerticalGrid(
                                    columns = GridCells.Adaptive(minSize = 138.dp),
                                    contentPadding = PaddingValues(8.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    items(parkingList) { parking ->
                                        val isAvailable =
                                            (parking.distance > 6.0)
                                        ParkingItemCard(
                                            parkCode = parking.parkCode,
                                            isAvailable = isAvailable
                                        )
                                    }
                                }
                            }
                        else -> {}
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
            .fillMaxSize()
            .aspectRatio(1f)
            .padding(8.dp)
            .background(
                color = if (isAvailable) {
                    Color.White
                } else {
                    Color(0xFFD9E1EB)
                },
                shape = MaterialTheme.shapes.medium
            )
            .border(
                width = 2.dp, color = if (isAvailable) {
                    Color.LightGray
                } else {
                    Color(0xFFD9E1EB)
                },
                shape = MaterialTheme.shapes.medium
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(8.dp)
        ) {
            Text(text = parkCode, fontSize = 36.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(16.dp))
            if (isAvailable) {
                Text("Parkir Kosong")
            } else {
                Image(
                    painter = painterResource(id = R.drawable.ic_car),
                    contentDescription = "Car Illustration",
                    modifier = Modifier.size(80.dp)
                )
            }
        }
    }
}