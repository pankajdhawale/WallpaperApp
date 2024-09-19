package com.example.wallpaperuserapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.wallpaperuserapp.R
import com.example.wallpaperuserapp.model.catModel

class CatAdapter(private val context: Context, private val listofcat: ArrayList<catModel>) : RecyclerView.Adapter<CatAdapter.BomViewHolder>() {

    inner class BomViewHolder(itemView: View) :ViewHolder(itemView)
    {
        val imageView=itemView.findViewById<ImageView>(R.id.cat_item)
        val name=itemView.findViewById<TextView>(R.id.cat_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BomViewHolder {
      val view=LayoutInflater.from(context).inflate(R.layout.edit_cat,parent,false)
        return BomViewHolder(view)
    }

    override fun onBindViewHolder(holder: BomViewHolder, position: Int) {
        holder.name.text=listofcat[position].name
        Glide.with(context).load(listofcat[position].link).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return listofcat.size
    }
}