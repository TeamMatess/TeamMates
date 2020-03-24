package com.wael.android.teammates.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController

import com.wael.android.teammates.R
import com.wael.android.teammates.databinding.FragmentRoomDetailsBinding
import com.wael.android.teammates.home.data.Room

/**
 * A simple [Fragment] subclass.
 */
class RoomDetails : Fragment() {
lateinit var binding:FragmentRoomDetailsBinding
    lateinit var room: Room
    // da 3aizen ngebo
    lateinit var roomID:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_room_details,container,false)
        room = Room()
        binding.room=room

        //hanb3t el room id lel fragment de 3shan ygeb el teams bta3t el room ele el id bt3ha da w 3shan ama y add team gded y2ado fl room de
        binding.viewTeamsBtn.setOnClickListener {findNavController().navigate(R.id.action_Details_to_teams) }
        return binding.root
    }

}
