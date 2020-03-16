package com.wael.android.teammates.sign.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.wael.android.teammates.R
import com.wael.android.teammates.sign.ViewModels.SignUpViewModel
import com.wael.android.teammates.sign.data.User
import kotlinx.android.synthetic.main.fragment_sign_up.*


/**
 * A simple [Fragment] subclass.
 */
class SignUp : Fragment() {

    private lateinit var viewModel: SignUpViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

        //   viewModel=ViewModelProvider(this).get(SignUpViewModel::class.java)

        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val user = User()
        user.email = "ahmedamerr@gmail.com"
        user.name = "assas"
        user.password = "123456789"
        user.phoneNumber = "01127251927"
        button_register.setOnClickListener {

            viewModel.signUpResult.observe(viewLifecycleOwner, Observer {
                if (it == null) {
                    Log.i("check yasta", "tam ya 3am")
                    viewModel.AddUser(user)
                } else {
                    Log.i("check yasta", "3awed mara okhra")

                }
            })

            viewModel.addUserResult.observe(viewLifecycleOwner, Observer {
                if (it == null) {
                    Log.i("check yasta", "dkhal elhamdulah")
                } else {
                    Log.i("check yasta", "3awed mara okhra")
                }
            })


            viewModel.signUp(user.email.toString(), user.password.toString())
//            viewModel.AddUser(user)

        }
    }

}
