package com.mikirinkode.smartparking.ui.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mikirinkode.smartparking.data.Parking
import com.mikirinkode.smartparking.ui.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class ParkingViewModel() : ViewModel() {

    private val _homeState: MutableStateFlow<UiState<List<Parking>>> =
        MutableStateFlow(UiState.Initial)
    val homeState: StateFlow<UiState<List<Parking>>> get() = _homeState

    private val database = Firebase.database
    private val myRef = database.getReference("parking")
    fun getData() {
        Log.e("HomeVM", "getData called")
        _homeState.value = UiState.Loading

        // Read from the database
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.e("HomeVM", "onDataChange")
                val parkingList = mutableListOf<Parking>()

                for (snapshot in dataSnapshot.children) {
                    val parking = snapshot.getValue(Parking::class.java)
                    if (parking != null) {
                        Log.e("HomeVM", "park ${parking.parkCode} = ${parking.distance}")
                        parkingList.add(parking)
                    }
                }
                _homeState.value = UiState.Success(parkingList)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.e("HomeVM", "on error :${error.message}")
            }
        })
    }

    fun getAvailableSlot(parkingList: List<Parking>): Int {
        var total = 0
        parkingList.forEach { parking ->
            if (parking.distance > 6.0)
                total++
        }
        return total
    }
}
