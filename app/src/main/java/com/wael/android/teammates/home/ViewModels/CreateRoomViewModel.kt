package com.wael.android.teammates.home.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.wael.android.teammates.home.data.Room
import java.lang.Exception

class CreateRoomViewModel :ViewModel(){

    private val _createRoomResult = MutableLiveData<Exception?>()
    val createRoomResult: LiveData<Exception?>
        get() = _createRoomResult

    fun createRoom(room: Room) {
        val w=1
        val database = FirebaseDatabase.getInstance().getReference("Rooms")
        room.roomId = database.push().key
        val code=room.roomId?.take(5)+w.toString()
        room.roomCode=code
        database.child(room.roomId!!).setValue(room)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _createRoomResult.value = null
                } else {
                    _createRoomResult.value = it.exception
                }
            }
    }
}