package com.surajrathod.bcaprograms.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.surajrathod.bcaprograms.R
import com.surajrathod.bcaprograms.data.Program
import com.surajrathod.bcaprograms.data.ProgramEntity

class NewAdapter(val context: Context) : RecyclerView.Adapter<NewAdapter.ViewHolder>(){

    var dataList = emptyList<ProgramEntity>()

    internal fun setDataList(dataList : List<ProgramEntity>){
        this.dataList = dataList
        notifyDataSetChanged()

    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        var title : TextView
        init {
            title = itemView.findViewById(R.id.txtTitle1)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.program_item,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data = dataList[position]

        holder.apply {
            title.text = data.title.toString()
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}