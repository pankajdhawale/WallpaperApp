package com.example.wallpaperuserapp.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaperuserapp.FinalWallpaperMainActivity
import com.example.wallpaperuserapp.R
import com.example.wallpaperuserapp.model.colortoneModel

class TheColorToneAdapter(
    private val context: Context,
    private val listcolortone: ArrayList<colortoneModel>
) : RecyclerView.Adapter<TheColorToneAdapter.BomViewHolder>() {

    inner class BomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardback: CardView = itemView.findViewById(R.id.card_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BomViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.edit_tct, parent, false)
        return BomViewHolder(view)
    }

    override fun onBindViewHolder(holder: BomViewHolder, position: Int) {
        val backcolor = listcolortone[position].color // Color string

        try {
            // Try to parse the color string and set it as the background color
            holder.cardback.setCardBackgroundColor(Color.parseColor(backcolor))
        } catch (e: IllegalArgumentException) {
            // If the color string is invalid, set a default color (e.g., GRAY)
            holder.cardback.setCardBackgroundColor(Color.GRAY)
        }
        holder.itemView.setOnClickListener{
            val intent= Intent(context, FinalWallpaperMainActivity::class.java)
            intent.putExtra("Link",listcolortone[position].link)
            context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return listcolortone.size
    }
}
