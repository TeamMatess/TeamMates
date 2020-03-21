package com.wael.android.teammates.sign.ViewModels

import android.provider.Settings.Global.getString
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.wael.android.teammates.R
import com.wael.android.teammates.sign.data.User
import java.lang.Exception
import kotlin.math.log

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

    fun verify(email:String){
        val actionCodeSettings = ActionCodeSettings.newBuilder()
            // URL you want to redirect back to. The domain (www.example.com) for this
            // URL must be whitelisted in the Firebase Console.
            .setUrl("https://www.example.com/finishSignUp?cartId=1234")
            // This must be true
            .setHandleCodeInApp(true)
            .setIOSBundleId("com.example.ios")
            .setAndroidPackageName(
                "com.example.android",
                true, /* installIfNotAvailable */
                "12" /* minimumVersion */)
            .build()


        mAuth.sendSignInLinkToEmail(email, actionCodeSettings)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.i("verify", "Email sent .")
                    Log.i("hel",task.result.toString())
                    Log.i("hel",task.result.toString())
                }
            }
    }
}