package com.example.wallpaperuserapp.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.wallpaperuserapp.CategoryActivity
import com.example.wallpaperuserapp.FinalWallpaperMainActivity
import com.example.wallpaperuserapp.R
import com.example.wallpaperuserapp.model.bomModel
import com.makeramen.roundedimageview.RoundedImageView

class CatImagesAdapter(private val context: Context, private val listImageCategory: ArrayList<bomModel>) : RecyclerView.Adapter<CatImagesAdapter.BomViewHolder>() {

    inner class BomViewHolder(itemView: View) :ViewHolder(itemView)
    {
        val imageView=itemView.findViewById<RoundedImageView>(R.id.cat_wallper)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BomViewHolder {
      val view=LayoutInflater.from(context).inflate(R.layout.cat_wallpaper,parent,false)
        return BomViewHolder(view)
    }

    override fun onBindViewHolder(holder: BomViewHolder, position: Int) {
        Glide.with(context).load(listImageCategory[position].link).override(300, 300).into(holder.imageView)
        holder.itemView.setOnClickListener{
            Log.d("tagkey", "Selected Category ID: ${listImageCategory[position].id}")
            val intent=Intent(context,CategoryActivity::class.java)
            intent.putExtra("uid",listImageCategory[position].id)
            context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return listImageCategory.size
    }
}