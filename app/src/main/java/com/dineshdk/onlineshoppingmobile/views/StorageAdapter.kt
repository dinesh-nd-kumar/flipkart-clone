package com.dineshdk.onlineshoppingmobile.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dineshdk.onlineshoppingmobile.R

class StorageAdapter(private val storageList: List<String>,val clickLisner:ItemClickListener) : RecyclerView.Adapter<StorageAdapter.StorageViewHolder>() {

    inner class StorageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val storageText: TextView = view.findViewById(R.id.storageText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StorageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_storage, parent, false)
        return StorageViewHolder(view)
    }

    override fun onBindViewHolder(holder: StorageViewHolder, position: Int) {
        holder.storageText.text = storageList[position]
        holder.itemView.setOnClickListener{
            clickLisner.onStorageItemClick(storageList[position])
        }
    }

    override fun getItemCount(): Int = storageList.size

    public interface ItemClickListener{
        public fun onStorageItemClick(p: String)
    }
}
