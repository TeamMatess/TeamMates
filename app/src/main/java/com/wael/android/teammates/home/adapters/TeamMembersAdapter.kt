package com.wael.android.teammates.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wael.android.teammates.databinding.TeamChildListItemBinding
import com.wael.android.teammates.home.data.Member

class TeamMembersAdapter :
    ListAdapter<Member, TeamMembersAdapter.ViewHolder>(MemberTypeCallBacks()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder private constructor(val binding: TeamChildListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Member) {
            binding.member = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TeamChildListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }


    }

    class MemberTypeCallBacks : DiffUtil.ItemCallback<Member>() {
        override fun areItemsTheSame(oldItem: Member, newItem: Member): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Member, newItem: Member): Boolean {
            return oldItem.equals(newItem)
        }

    }


}