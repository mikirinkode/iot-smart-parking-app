package com.mikirinkode.smartparking.ui.comp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mikirinkode.smartparking.R

@Composable
fun ParkingSlotItemCard(
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