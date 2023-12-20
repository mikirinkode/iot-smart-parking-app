package com.mikirinkode.smartparking.ui.comp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BuildingItemCard(
    parkingName: String,
    availableSlot: Int,
    maxSlot: Int,
    onItemClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = MaterialTheme.shapes.medium
            )
            .clip(shape = MaterialTheme.shapes.medium)
            .clickable(onClick = onItemClicked)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp,)
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
            ) {
                if (availableSlot == 0) {
                    Text(
                        text = "Parkir Penuh",
                        color = Color.Gray,
                        modifier = Modifier
                            .border(
                                width = 2.dp,
                                color = Color.Gray,
                                shape = MaterialTheme.shapes.medium
                            )
                            .background(
                                color = Color.Gray.copy(alpha = 0.2f),
                                shape = MaterialTheme.shapes.medium
                            )
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                } else {
                    Text(
                        text = "$availableSlot Slot Tersedia",
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .border(
                                width = 2.dp,
                                color = MaterialTheme.colorScheme.primary,
                                shape = MaterialTheme.shapes.medium
                            )
                            .background(
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                                shape = MaterialTheme.shapes.medium
                            )
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = parkingName, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(text = "$availableSlot / $maxSlot Slot", fontSize = 12.sp)
            }
        }
    }
}