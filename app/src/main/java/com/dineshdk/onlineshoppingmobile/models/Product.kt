package com.dineshdk.onlineshoppingmobile.models

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.dineshdk.onlineshoppingmobile.R
import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    val id: Int,

    @SerializedName("productCategory")
    val productCategory: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("brand")
    val brand: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("basePrice")
    val basePrice: Int,

    @SerializedName("inStock")
    val inStock: Boolean,

    @SerializedName("stock")
    val stock: Int,

    @SerializedName("featuredImage")
    val featuredImage: String,

    @SerializedName("thumbnailImage")
    val thumbnailImage: String,

    @SerializedName("storageOptions")
    val storageOptions: List<String>,

    @SerializedName("colorOptions")
    val colorOptions: List<String>,

    @SerializedName("display")
    val display: String,

    @SerializedName("CPU")
    val cpu: String,

    @SerializedName("GPU")
    val gpu: String? = null,  // Optional field

    @SerializedName("camera")
    val camera: Camera?
){
    companion object{
        @JvmStatic
        @BindingAdapter("android:loadImage")
        fun loadImg(v:ImageView,url: String){
            Glide.with(v)
                .load(url)
                .fitCenter()
                .into(v)

        }

        @JvmStatic
        @BindingAdapter("android:loadColorText")
        fun loadColorText(v:TextView,list: List<String>){
            list.let {
            v.text = it[0]
            }
        }
        @JvmStatic
        @BindingAdapter("android:loadCountText")
        fun loadCountText(v:TextView,list: List<String>){
            list.let {
                v.text = "${it.size} More"
            }
        }
        @JvmStatic
        @BindingAdapter("android:loadInStockText")
        fun loadInStockText(v:TextView, p: ProductEntity){
            if (p.inStock) {
                v.text = "In Stock(${p.stock})"
                v.setTextColor(v.context.getColor(R.color.green))
            }else{
                v.text = "Out of Stock"
                v.setTextColor(v.context.getColor(R.color.red))
            }
        }

    }

}

data class Camera(
    @SerializedName("rearCamera")
    val rearCamera: String?,

    @SerializedName("frontCamera")
    val frontCamera: String?
)

