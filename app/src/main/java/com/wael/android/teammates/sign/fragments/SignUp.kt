package com.wael.android.teammates.sign.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.wael.android.teammates.R
import com.wael.android.teammates.databinding.FragmentSignUpBinding
import com.wael.android.teammates.sign.ViewModels.SignUpViewModel
import com.wael.android.teammates.sign.data.User
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_sign_up.*


/**
 * A simple [Fragment] subclass.
 */
class SignUp : Fragment() {
lateinit var binding:FragmentSignUpBinding
    lateinit var user: User
    private lateinit var viewModel: SignUpViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_sign_up,container,false)
        user= User()
        // da ele ha7ot feh el value ele gaya mn el layout
        binding.user=user
        //da el user ele fl fragment layout
        //binding.user=user


        //   viewModel=ViewModelProvider(this).get(SignUpViewModel::class.java)

      //
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
       /* val user = User()
        user.email = "ahmedamerr@gmail.com"
        user.name = "assas"
        user.password = "123456789"
        user.phoneNumber = "01127251927"*/
        button_register.setOnClickListener {

            viewModel.signUpResult.observe(viewLifecycleOwner, Observer {
                if (it == null) {
                    Log.i("check yasta", "tam ya 3am")
                    viewModel.verify(user.email.toString())

                    button_register.setOnClickListener { findNavController().navigate(R.id.action_signUp_to_home2) }
                    viewModel.AddUser(user)
                } else {
                    button_register.setOnClickListener { findNavController().navigate(R.id.action_signUp_to_home2) }
                    Log.i("check yasta", "3awed mara okhra")
                    viewModel.verify(user.email.toString())

                }
            })

            viewModel.addUserResult.observe(viewLifecycleOwner, Observer {
                if (it == null) {
                    Log.i("check yasta", "dkhal elhamdulah")
                } else {
                    Log.i("check yasta", "3awed mara okhra")
                }
            })



//            viewModel.AddUser(user)
            //check 3al views of fragment fun 1
            // law el views kamla
                // signup from viewmodel
            viewModel.signUp(binding?.user?.email.toString(), binding?.user?.password.toString())


        }

//        button2.setOnClickListener { viewModel.verify() }
    }




}
