package com.dineshdk.onlineshoppingmobile.repo

import com.dineshdk.onlineshoppingmobile.models.Product
import retrofit2.Call
import retrofit2.http.GET

interface ProductApis {
    @GET("api/products")
    fun getProducts(): Call<List<Product>>
}