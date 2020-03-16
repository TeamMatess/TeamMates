package com.wael.android.teammates.sign.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.wael.android.teammates.R
import com.wael.android.teammates.sign.ViewModels.LoginViewModel
import com.wael.android.teammates.sign.ViewModels.SignUpViewModel
import com.wael.android.teammates.sign.data.User
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * A simple [Fragment] subclass.
 */
class Login : Fragment() {
    private lateinit var viewModel: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val user = User()
        user.email = "ahmedamerr@gmail.com"
        user.password = "123456789"
        viewModel.loginResult.observe(viewLifecycleOwner,
            Observer {
                if (it == null) {
                    Log.i("login Check","wsaal wsaal ")
                } else {
                    Log.i("login Check",it.message.toString())
                }
            })
        button_sign_in.setOnClickListener {
           viewModel.login(user.email.toString(),user.password.toString())
        }
    }

}
