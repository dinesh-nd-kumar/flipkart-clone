package com.dineshdk.onlineshoppingmobile.others

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dineshdk.onlineshoppingmobile.databinding.RowItemBinding
import com.dineshdk.onlineshoppingmobile.models.ProductEntity

class ProductAdapter(val clickListener: ItemClickListener, var productList: List<ProductEntity>?)
    : RecyclerView.Adapter<ProductAdapter.UniversityViewHolder>() {
//        private var key : List<String> = productList?.products?.keys!!.map { it }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniversityViewHolder {
        return UniversityViewHolder(
            RowItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false))
    }

    override fun getItemCount(): Int {

        if (productList == null)
            return 0
        return productList!!.size

    }


    override fun onBindViewHolder(holder: UniversityViewHolder, position: Int) {
        val name = productList?.get(position)
        if (name != null) {
            holder.bindData(name)
        }

    }


    inner class UniversityViewHolder(val binding:RowItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(p: ProductEntity?){
            binding.apply {
                product = p
                ratingTextview.text = "${p?.stock} "
                root.setOnClickListener{
                    clickListener.onItemClick(p!!)
                }
            }
        }

    }
    public interface ItemClickListener{
        public fun onItemClick(p: ProductEntity)
    }
}