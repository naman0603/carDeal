package com.example.cardealapplication.dataAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cardealapplication.R
import com.example.cardealapplication.dataModel.InfoCarModelDataModel
import com.example.cardealapplication.dataModel.PurchaseDataModel
import com.example.cardealapplication.viewHolder.InfoCarModelViewHolder

class InfoCarModelDataAdapter(val context: Context,val model: ArrayList<InfoCarModelDataModel>):
    RecyclerView.Adapter<InfoCarModelViewHolder>() {
    var onItemClick : ((InfoCarModelDataModel) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoCarModelViewHolder {
        return InfoCarModelViewHolder(LayoutInflater.from(context).inflate(R.layout.car_model_display,parent,false))
    }

    override fun getItemCount(): Int = model.size

    override fun onBindViewHolder(holder: InfoCarModelViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.txtCompanyName).text=model[position].txtCompanyName
        Glide.with(context).load(model[position].imgLogo).into(holder.itemView.findViewById(R.id.imgLogo))

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(model[position])
        }
    }
}