package com.wael.android.teammates.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.wael.android.teammates.R
import com.wael.android.teammates.databinding.FragmentAddTeamsBinding
import com.wael.android.teammates.home.ViewModels.AddTeamViewModel
import com.wael.android.teammates.home.ViewModels.CreateRoomViewModel
import com.wael.android.teammates.home.data.Team
import java.lang.reflect.Member

/**
 * A simple [Fragment] subclass.
 */
class AddTeams : DialogFragment() {
    lateinit var binding: FragmentAddTeamsBinding
    lateinit var team: Team
    lateinit var viewModel: AddTeamViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this).get(AddTeamViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_teams, container, false)
        team = Team()
        binding.team = team
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.addRoomResult.observe(viewLifecycleOwner, Observer {
            if (it == null) {
                findNavController().navigate(R.id.action_addTeams_to_teams)
            }
            else{
                Toast.makeText(context,it.message,Toast.LENGTH_LONG).show()
            }
        })
        binding.addMemberButton.setOnClickListener {
            viewModel.createTeam(
                team,
                // hna 3aizen n7ot variable room id
                "-M3D-vVkuza7EAr5O598"
            )
        }

    }
}
