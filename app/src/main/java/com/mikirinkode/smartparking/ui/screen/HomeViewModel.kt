package com.mikirinkode.smartparking.ui.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mikirinkode.smartparking.ui.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class Parking(
    val parkCode: String = "",
    val distance: Double = 0.0,
)

class HomeViewModel() : ViewModel() {


    private val _homeState: MutableStateFlow<UiState<List<Parking>>> =
        MutableStateFlow(UiState.Initial)
    val homeState: StateFlow<UiState<List<Parking>>> get() = _homeState

    private val database = Firebase.database
    private val myRef = database.getReference("parking")
    private val myMessage = database.getReference("message")


    fun getData() {
//        myMessage.addValueEventListener(object: ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                TODO("Not yet implemented")
//        Log.e("HomeVM", "on data change")
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//        Log.e("HomeVM", "on error")
//            }
//        })

        Log.e("HomeVM", "getData called")
        _homeState.value = UiState.Loading
//        val receiveListener = object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                Log.e("HomeVM", "on data change")
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.e("HomeVM", "on error")
//            }
//
//        }
//
//        myRef.addValueEventListener(receiveListener)


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
}
