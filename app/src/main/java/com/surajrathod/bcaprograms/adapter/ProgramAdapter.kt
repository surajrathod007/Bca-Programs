package com.surajrathod.bcaprograms.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.surajrathod.bcaprograms.data.Program
import com.surajrathod.bcaprograms.data.ProgramDatabase
import com.surajrathod.bcaprograms.data.ProgramEntity
import com.surajrathod.bcaprograms.databinding.ProgramItemBinding
import com.surajrathod.bcaprograms.model.RemoteProgram

class ProgramAdapter(private val onItemClicked : (RemoteProgram) -> Unit) : ListAdapter<RemoteProgram,ProgramAdapter.ProgramViewHolder>(
    DiffCallback){


    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<RemoteProgram>() {
            override fun areItemsTheSame(oldItem: RemoteProgram, newItem: RemoteProgram): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RemoteProgram, newItem: RemoteProgram): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ProgramViewHolder(private var binding: ProgramItemBinding):RecyclerView.ViewHolder(binding.root)
    {
        fun bind(program: RemoteProgram)
        {
            binding.txtTitle1.text = program.title.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramViewHolder {
        val viewHolder = ProgramViewHolder(ProgramItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))


        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ProgramViewHolder, position: Int) {
        holder.bind(getItem(position))


    }
}




