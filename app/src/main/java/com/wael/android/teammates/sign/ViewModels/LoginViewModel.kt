package com.wael.android.teammates.sign.ViewModels

import android.R.attr
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth


class LoginViewModel : ViewModel() {
    private lateinit var mAuth: FirebaseAuth
    private val _loginResult = MutableLiveData<Exception?>()
    val loginResult: LiveData<Exception?>
        get() = _loginResult

    fun login(email: String, password: String) {

        mAuth = FirebaseAuth.getInstance()
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                    _loginResult.value=null

            } else {
                _loginResult.value=task.exception
            }
        }


        // ...
    }
}
