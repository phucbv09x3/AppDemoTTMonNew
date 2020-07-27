package com.monstar_lab_lifetime.appdemottmon.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.monstar_lab_lifetime.appdemottmon.model.MesData
import com.monstar_lab_lifetime.appdemottmon.R
import com.monstar_lab_lifetime.appdemottmon.adapter.MesAdapter.MesViewHolder

class MesAdapter(
) : Adapter<MesViewHolder>() {
    private var list:MutableList<MesData> = mutableListOf()
    class MesViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var mNameMess=itemView.findViewById(R.id.tv_nameMes) as TextView
        var mImage=itemView.findViewById(R.id.cv_message) as? ImageView
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MesViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_message, parent, false)
        return MesViewHolder(v)
    }
    fun setList(list:MutableList<MesData>){
        this.list=list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MesViewHolder, position: Int) {
        var list=list[position]
        holder.mNameMess.text= list.name
        holder.mImage?.setImageResource(list.image!!)
    }
}