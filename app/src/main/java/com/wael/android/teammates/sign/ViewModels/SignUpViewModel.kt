package com.wael.android.teammates.sign.ViewModels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.wael.android.teammates.sign.data.User
import java.lang.Exception

class SignUpViewModel : ViewModel() {
    private lateinit var mAuth: FirebaseAuth
    private val _result = MutableLiveData<Exception?>()
    val addUserResult: LiveData<Exception?>
        get() = _result

    private val _signUpResult = MutableLiveData<Exception?>()
    val signUpResult: LiveData<Exception?>
        get() = _signUpResult

    fun AddUser(user: User) {
        val database = FirebaseDatabase.getInstance().getReference("Users")
        user.id = database.push().key
        database.child(user.id!!).setValue(user)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _result.value = null
                } else {
                    _result.value = it.exception
                }
            }
    }

     fun signUp(email: String, password: String) {
         mAuth = FirebaseAuth.getInstance()
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.i("sign", "signedUp")
                    _signUpResult.value = null
                } else {
                    Log.i("signUp", "Error")
                    _signUpResult.value = task.exception
                }
            }
    }
}