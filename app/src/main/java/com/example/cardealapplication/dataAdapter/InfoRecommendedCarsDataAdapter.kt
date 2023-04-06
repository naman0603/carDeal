package com.example.cardealapplication.dataAdapter

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cardealapplication.R
import com.example.cardealapplication.dataModel.HomeRecommendedCarsModelData
import com.example.cardealapplication.viewHolder.InfoRecommendedCarsViewHolder

class InfoRecommendedCarsDataAdapter(val context: Context,val model : ArrayList<HomeRecommendedCarsModelData> ):
    RecyclerView.Adapter<InfoRecommendedCarsViewHolder>(){
    var onItemClick : ((HomeRecommendedCarsModelData) -> Unit)? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InfoRecommendedCarsViewHolder {
        return InfoRecommendedCarsViewHolder(LayoutInflater.from(context).inflate(R.layout.car_info_display,parent,false))
    }

    override fun getItemCount(): Int = model.size

    override fun onBindViewHolder(holder: InfoRecommendedCarsViewHolder, position: Int) {
        val txtPrice = "<b>₹</b> "+model[position].txtPrice

        holder.itemView.findViewById<TextView>(R.id.txtName).text=model[position].txtName
        holder.itemView.findViewById<TextView>(R.id.txtPrice).text=Html.fromHtml(txtPrice)
        holder.itemView.findViewById<TextView>(R.id.txtType).text=model[position].txtType

        Glide.with(context).load(model[position].imgCarImage).into(holder.itemView.findViewById(R.id.imgCarImage))
        Glide.with(context).load(model[position].imgLogo).into(holder.itemView.findViewById(R.id.imgLogo))

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(model[position])
        }
    }

}