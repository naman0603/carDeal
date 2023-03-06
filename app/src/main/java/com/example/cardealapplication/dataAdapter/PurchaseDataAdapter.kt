package com.example.cardealapplication.dataAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.example.cardealapplication.dataModel.PurchaseDataModel
import com.example.cardealapplication.R
import com.example.cardealapplication.viewHolder.PurchaseViewHolder

class PurchaseDataAdapter(val context:Context,val model:java.util.ArrayList<PurchaseDataModel>):RecyclerView.Adapter<PurchaseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseViewHolder {
        return PurchaseViewHolder(LayoutInflater.from(context).inflate(R.layout.purchase_view_1,parent,false))
    }

    override fun getItemCount():Int=model.size

    override fun onBindViewHolder(holder: PurchaseViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.txtCarName).text=model[position].txtCarName
        holder.itemView.findViewById<TextView>(R.id.txtCarPrice).text=model[position].txtCarPrice
        holder.itemView.findViewById<TextView>(R.id.txtManYear).text=model[position].txtManYear
        Glide.with(context).load(model[position].img).into(holder.itemView.findViewById(R.id.image))
    }
}