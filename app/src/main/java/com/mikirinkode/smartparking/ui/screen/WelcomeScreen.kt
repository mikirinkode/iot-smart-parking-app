package com.mikirinkode.smartparking.ui.screen


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mikirinkode.smartparking.R
import com.mikirinkode.smartparking.ui.theme.SmartParkingTheme

@Composable
fun WelcomeScreen(
    onStartClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_riding_car))

    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
    )

    Surface(
        color = Color.White
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier.padding(16.dp)
            ) {
                Column() {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Selamat Datang di",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "Parkirin.",
                        fontWeight = FontWeight.Bold,
                        fontSize = 48.sp,
                        modifier = Modifier.padding(vertical = 0.dp)
                    )
                    Text(
                        text = "Gak perlu mikirin cari tempat parkir lagi karna sekarang semakin mudah dengan Parkirin.",
                        fontSize = 14.sp,
                        modifier = Modifier.offset(y = (-12).dp),
                        color = Color.Gray
                    )
                }
            }
            Box(
                modifier = Modifier.weight(1f)
            ) {
                LottieAnimation(
                    composition = composition,
                    progress = { progress },
                    modifier = modifier
                        .size(400.dp)
                        .align(Alignment.Center)

                )
            }
            Box(
                modifier = Modifier.padding(16.dp)
            ) {
                Button(
                    onClick = {
                        onStartClicked()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row {
                        Text(text = "Cari Tempat", fontSize = 18.sp, fontWeight = FontWeight.Medium)
                        Spacer(modifier = Modifier.width(16.dp))
                        Icon(
                            imageVector = Icons.Rounded.ArrowForward,
                            contentDescription = "Arrow Forward"
                        )
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    SmartParkingTheme {
        WelcomeScreen(onStartClicked = { })
    }
}