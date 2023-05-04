package com.example.cardealapplication.dataAdapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cardealapplication.R
import com.example.cardealapplication.dataModel.TestDriveDataModel
import com.example.cardealapplication.viewHolder.TestDriveViewHolder

class TestDriveDataAdapter(val context : Context,val model : ArrayList<TestDriveDataModel>):
RecyclerView.Adapter<TestDriveViewHolder>(){
    var onItemClick : ((TestDriveDataModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestDriveViewHolder {
        return TestDriveViewHolder(LayoutInflater.from(context).inflate(R.layout.purchase_view_1,parent,false))
    }

    override fun getItemCount(): Int = model.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TestDriveViewHolder, position: Int) {

        val txtCarPrice = "<b>₹</b> "+model[position].price
        holder.itemView.findViewById<TextView>(R.id.txtCarName).text=model[position].txtCarName
        holder.itemView.findViewById<TextView>(R.id.txtCarPrice).text= Html.fromHtml(txtCarPrice)
        holder.itemView.findViewById<TextView>(R.id.txtPlace).text=
            "${model[position].city}, ${model[position].state}"

        Glide.with(context).load(model[position].img).into(holder.itemView.findViewById(R.id.image))
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(model[position])
        }
    }
}