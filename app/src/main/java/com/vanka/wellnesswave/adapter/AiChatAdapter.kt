package com.vanka.wellnesswave.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vanka.wellnesswave.databinding.FragmentAIChatBinding

class AiChatAdapter(val context:Context,val list:ArrayList<String>):RecyclerView.Adapter<AiChatAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: FragmentAIChatBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FragmentAIChatBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }
}