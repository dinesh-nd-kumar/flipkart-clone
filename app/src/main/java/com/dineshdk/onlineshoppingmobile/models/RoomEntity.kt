package com.dineshdk.onlineshoppingmobile.models

import androidx.room.*
import com.dineshdk.onlineshoppingmobile.others.Converters

@Entity(tableName = "product")
@TypeConverters(Converters::class) // Apply converter to the entity if it applies to multiple fields
data class ProductEntity(
    @PrimaryKey val id: Int,
    val productCategory: String,
    val name: String,
    val brand: String,
    val description: String,
    val basePrice: Int,
    val inStock: Boolean,
    val stock: Int,
    val featuredImage: String,
    val thumbnailImage: String,
    @TypeConverters(Converters::class)
    val storageOptions: List<String>,
    @TypeConverters(Converters::class)
    val colorOptions: List<String>,
    val display: String?,
    val CPU: String?,
    val rearCamera: String?,
    val frontCamera: String?,
    val GPU: String? = null // Optional in case some products do not have a GPU
){

}

