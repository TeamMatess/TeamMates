package com.wael.android.teammates.home.fragments

import android.os.Bundle
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
import com.wael.android.teammates.databinding.FragmentAddRoomBinding
import com.wael.android.teammates.home.ViewModels.CreateRoomViewModel
import com.wael.android.teammates.home.data.Room

/**
 * A simple [Fragment] subclass.
 */
class AddRoom : Fragment() {
lateinit var binding:FragmentAddRoomBinding
    lateinit var room: Room
    lateinit var viewModel: CreateRoomViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_room, container, false)
        viewModel= ViewModelProvider(this).get(CreateRoomViewModel::class.java)
        room = Room()

        binding.room = room
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.createRoomBtn.setOnClickListener {viewModel.createRoom(room)  }

        viewModel.createRoomResult.observe(viewLifecycleOwner, Observer {

            if(it==null){
                Toast.makeText(context,"Room Created Successfully ", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_create_room_to_details)

            }
            else{
                Toast.makeText(context,it.message, Toast.LENGTH_LONG).show()

            }
        })

    }
}
