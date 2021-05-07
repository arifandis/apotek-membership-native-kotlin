package com.cahstudio.tesapotekk24.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cahstudio.tesapotekk24.R
import com.cahstudio.tesapotekk24.datasource.remote.model.MemberListResponse

class MemberAdapter(val context: Context, val memberList: List<MemberListResponse.Member>):
    RecyclerView.Adapter<MemberAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_member, parent, false))
    }

    override fun onBindViewHolder(holder: MemberAdapter.ViewHolder, position: Int) {
        holder.bindData(memberList[position])
    }

    override fun getItemCount(): Int = memberList.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val tvName: TextView = view.findViewById(R.id.tvName)
        private val tvDateOfBirth: TextView = view.findViewById(R.id.tvDateOfBirth)
        private val tvRegistered: TextView = view.findViewById(R.id.tvRegistered)

        fun bindData(member: MemberListResponse.Member) {
            tvName.text = ": ${member.name}"
            tvDateOfBirth.text = ": ${member.dateOfBirth}"
            tvRegistered.text = ": ${member.registered}"
        }
    }
}