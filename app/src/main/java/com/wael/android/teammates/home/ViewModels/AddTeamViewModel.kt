package com.wael.android.teammates.home.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import com.wael.android.teammates.home.data.Room
import com.wael.android.teammates.home.data.Team
import java.lang.Exception

class AddTeamViewModel : ViewModel() {
    private val _AddTeamResult = MutableLiveData<Exception?>()
    val addRoomResult: LiveData<Exception?>
        get() = _AddTeamResult

    fun createTeam(team: Team,roomId:String) {
        val database = FirebaseDatabase.getInstance().getReference("Rooms").child(roomId).child("Teams")
        team.teamId = database.push().key.toString()
        database.child(team.teamId!!).setValue(team)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _AddTeamResult.value = null
                } else {
                    _AddTeamResult.value = it.exception
                }
            }
    }
}