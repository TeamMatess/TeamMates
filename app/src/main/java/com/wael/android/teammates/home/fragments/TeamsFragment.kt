package com.wael.android.teammates.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.wael.android.teammates.R
import com.wael.android.teammates.databinding.FragmentTeamsBinding
import com.wael.android.teammates.home.adapters.TeamsAdapter
import com.wael.android.teammates.home.data.Member
import com.wael.android.teammates.home.data.Team
import com.wael.android.teammates.sign.data.User

/**
 * A simple [Fragment] subclass.
 */
class TeamsFragment : Fragment() {
lateinit var binding:FragmentTeamsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_teams,container,false)

        val user =User("wael","wael","wael@gmail.com","wwww","sjjs","01138")

        val member=Member(user,"123","123")
        val list = mutableListOf(member, member, member, member, member, member)
        val Team= Team("madried","123",list)
        val teams = mutableListOf(Team, Team, Team, Team, Team, Team)
        val teamsAdapter=TeamsAdapter()
        teamsAdapter.submitList(teams)
        binding.typesList.adapter=teamsAdapter
        return binding.root

    }

}
