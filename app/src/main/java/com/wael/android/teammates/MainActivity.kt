package com.wael.android.teammates

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.wael.android.teammates.home.data.Member
import com.wael.android.teammates.sign.data.User
import com.wael.android.teammates.sign.fragments.Login
import com.wael.android.teammates.sign.fragments.SignUp
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_sign_up.*

class MainActivity : AppCompatActivity() {
private val login=Login()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val fragmentTransaction = supportFragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.container1, login)
//        fragmentTransaction.commit()

        val user = User()
        user.email = "ahmedamerr@gmail.com"
        user.name = "assas"
        user.password = "123456789"
        user.phoneNumber = "01127251927"
        val member = Member(user,"room id ","team id ")
        Log.i("user as member ",member.name.toString())
    }

}
