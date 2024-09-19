package com.example.wallpaperuserapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.wallpaperuserapp.FinalWallpaperMainActivity
import com.example.wallpaperuserapp.R
import com.example.wallpaperuserapp.model.bomModel

class BomAdapter(private val context: Context, private val bestofmonth: ArrayList<bomModel>) : RecyclerView.Adapter<BomAdapter.BomViewHolder>() {

    inner class BomViewHolder(itemView: View) :ViewHolder(itemView)
    {
        val imageView=itemView.findViewById<ImageView>(R.id.image_bom)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BomViewHolder {
      val view=LayoutInflater.from(context).inflate(R.layout.edit_bom,parent,false)
        return BomViewHolder(view)
    }

    override fun onBindViewHolder(holder: BomViewHolder, position: Int) {
        Glide.with(context).load(bestofmonth[position].link).override(300, 300).into(holder.imageView)
        holder.itemView.setOnClickListener{
            val intent=Intent(context,FinalWallpaperMainActivity::class.java)
            intent.putExtra("Link",bestofmonth[position].link)
            context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return bestofmonth.size
    }
}