package com.mikirinkode.smartparking.ui.comp

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ParkingSideBar(
    topEndPercent: Int = 0,
    bottomEndPercent: Int = 0,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
            .border(
                2.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(
                    topEndPercent = topEndPercent,
                    bottomEndPercent = bottomEndPercent
                )
            )
    )
}