package com.example.cardealapplication.dataAdapter

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cardealapplication.R
import com.example.cardealapplication.dataModel.InfoCarDetailsDataModel
import com.example.cardealapplication.viewHolder.InfoCarDetailsViewHolder

class InfoCarDetailsDataAdapter(val context: Context,val model : ArrayList<InfoCarDetailsDataModel>):
    RecyclerView.Adapter<InfoCarDetailsViewHolder>() {
    var onItemClick : ((InfoCarDetailsDataModel) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoCarDetailsViewHolder {
        return InfoCarDetailsViewHolder(LayoutInflater.from(context).inflate(R.layout.info_car_details,parent,false))
    }

    override fun getItemCount(): Int = model.size

    override fun onBindViewHolder(holder: InfoCarDetailsViewHolder, position: Int) {
        val txtPriceRange = "<b>₹ </b>"+model[position].txtPriceRange

        holder.itemView.findViewById<TextView>(R.id.txtCarName).text=model[position].txtCarName
        holder.itemView.findViewById<TextView>(R.id.txtTransmission).text= model[position].txtTransmission
        holder.itemView.findViewById<TextView>(R.id.txtCarPrice).text=Html.fromHtml(txtPriceRange)
        holder.itemView.findViewById<TextView>(R.id.txtCarType).text=model[position].txtType

        Glide.with(context).load(model[position].imgCarView).into(holder.itemView.findViewById(R.id.imgCarView))

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(model[position])
        }

    }

}
