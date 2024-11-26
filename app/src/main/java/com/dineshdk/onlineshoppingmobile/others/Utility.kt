package com.dineshdk.onlineshoppingmobile.others



import androidx.room.TypeConverter
import com.dineshdk.onlineshoppingmobile.models.Product
import com.dineshdk.onlineshoppingmobile.models.ProductEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromStringList(value: List<String>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType)
    }
}


fun List<Product>.toEntity(): List<ProductEntity> {

    return this.map { p ->
        ProductEntity(
            id = p.id,
            productCategory = p.productCategory,
            name = p.name,
            brand = p.brand,
            description = p.description,
            basePrice = p.basePrice,
            inStock = p.inStock,
            stock = p.stock,
            featuredImage = p.featuredImage,
            thumbnailImage = p.thumbnailImage,
            storageOptions = p.storageOptions,
            colorOptions = p.colorOptions,
            display = p.display,
            CPU = p.cpu,
            rearCamera = p.camera?.rearCamera,
            frontCamera = p.camera?.frontCamera,
            GPU = p.gpu // Optional field
        )
    }


}


