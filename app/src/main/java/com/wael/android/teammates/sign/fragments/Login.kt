package com.wael.android.teammates.sign.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.wael.android.teammates.R
import com.wael.android.teammates.databinding.FragmentLoginBinding
import com.wael.android.teammates.sign.ViewModels.LoginViewModel
import com.wael.android.teammates.sign.ViewModels.SignUpViewModel
import com.wael.android.teammates.sign.data.User
import kotlinx.android.synthetic.main.fragment_login.*


class Login : Fragment() {
    lateinit var user:User
    private lateinit var viewModel: LoginViewModel
    lateinit var loginBinding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        // Inflate the layout for this fragment
        user=User()
        loginBinding.user=user
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)


        loginBinding.signupText.setOnClickListener {findNavController().navigate(R.id.action_login_fragment_to_signUp)  }

        return loginBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

     //  user=loginBinding.user

        viewModel.loginResult.observe(viewLifecycleOwner,
            Observer {
                if (it == null) {
                    Log.i("login Check", "wsaal wsaal ")
                    findNavController().navigate(R.id.action_login_fragment_to_nav3)
                } else {
                    Log.i("login Check", it.message.toString())
                  Toast.makeText(context,it.message,Toast.LENGTH_LONG).show()
                }
            })
        button_sign_in.setOnClickListener {
            viewModel.login(loginBinding?.user?.email.toString(), loginBinding?.user?.password.toString())
        }
    }

}
