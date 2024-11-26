package com.dineshdk.onlineshoppingmobile.views

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dineshdk.onlineshoppingmobile.R

class ColorAdapter(private val colorList: List<String>,val clickLisner: ColorItemClickListener) : RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {

    inner class ColorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val colorImage: ImageView = view.findViewById(R.id.colorImage)
        val colorName: TextView = view.findViewById(R.id.colorName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_color, parent, false)
        return ColorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val colorOption = colorList[position]
//        holder.colorImage.setImageResource(colorOption.imageResId)
        try {
            val colorInt = Color.parseColor(colorOption.toString())
            holder.colorImage.setBackgroundColor(colorInt)
        } catch (e: IllegalArgumentException) {
            // Handle the case where the color name is not recognized
            Log.e("ColorError", "Color not recognized: $colorOption")
        }
        holder.colorName.text = colorOption
        holder.itemView.setOnClickListener{
            clickLisner.onColorItemClick(colorOption)
        }
    }

    override fun getItemCount(): Int = colorList.size

    public interface ColorItemClickListener{
        public fun onColorItemClick(p: String)
    }
}

//data class ColorOption(val name: String, val imageResId: Int)
