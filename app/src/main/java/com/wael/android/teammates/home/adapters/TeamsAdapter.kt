package com.wael.android.teammates.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wael.android.teammates.databinding.TeamChildListItemBinding
import com.wael.android.teammates.databinding.TeamsParentRecyclerviewItemBinding
import com.wael.android.teammates.home.data.Member
import com.wael.android.teammates.home.data.Team

class TeamsAdapter :
    ListAdapter<Team, TeamsAdapter.ViewHolder>(TeamAdapterCallBacks()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    /* class ViewHolder private constructor(val binding: TeamsParentRecyclerviewItemBinding) :
         RecyclerView.ViewHolder(binding.root) {
         fun bind(item: Team) {
             binding.team = item
             binding.executePendingBindings()
         }

         companion object {
             fun from(parent: ViewGroup): ViewHolder {
                 val layoutInflater = LayoutInflater.from(parent.context)
                 val binding = TeamsParentRecyclerviewItemBinding.inflate(layoutInflater, parent, false)
                 return ViewHolder(binding)
             }
         }


     }*/


    class ViewHolder private constructor(val binding: TeamsParentRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {



        fun bind(item: Team) {
            val childLayoutManager =
                LinearLayoutManager(binding.rvChild.context, RecyclerView.HORIZONTAL, false)
            binding.team = item
            binding.executePendingBindings()

            //fill the teams list by take the item members and fill every item with it's members
            val teamMembersAdapter = TeamMembersAdapter()
            binding.rvChild.layoutManager = childLayoutManager

            teamMembersAdapter.submitList(item.members)
            binding.rvChild.adapter = teamMembersAdapter


            //setRecycledViewPool(viewPool)
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    TeamsParentRecyclerviewItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }
    class TeamAdapterCallBacks : DiffUtil.ItemCallback<Team>() {
        override fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean {
            return oldItem.teamId == newItem.teamId
        }

        override fun areContentsTheSame(oldItem: Team, newItem: Team): Boolean {
            return oldItem.equals(newItem)
        }

    }

}


