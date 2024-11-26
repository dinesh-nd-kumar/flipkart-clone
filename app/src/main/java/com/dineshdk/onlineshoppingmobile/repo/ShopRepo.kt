package com.dineshdk.onlineshoppingmobile.repo

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.dineshdk.onlineshoppingmobile.models.Product
import com.dineshdk.onlineshoppingmobile.models.ProductEntity
import com.dineshdk.onlineshoppingmobile.others.toEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShopRepo {
    private var productLiveData = MutableLiveData<List<ProductEntity>>()
    var context:Context? = null

    suspend fun loadData(context: Context) {
        this.context = context
        productLiveData.postValue( getProductFromRoom())
        fetchProducts()
    }
    fun getData(): MutableLiveData<List<ProductEntity>> {
        return productLiveData
    }

     private suspend fun fetchProducts() {
        val call = Retrofit.api.getProducts()
        call.enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                if (response.isSuccessful) {
                    // Handle the successful response
                    val products = response.body()
                    Log.d("ShopRepo", "Products: $products")

                    productLiveData.postValue( response.body()?.toEntity())
                    GlobalScope.launch{
                    saveProductInRoom(response.body())
                }

                    products?.forEach { p ->
                        Log.d("Product", "${p.brand}: ${p.name}, Price: ${p.basePrice}")
                    }

                } else {
                    Log.e("ShopRepo", "Failed: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Log.e("MainActivity", "Error: ${t.message}")
            }
        })
    }

    suspend fun saveProductInRoom(body: List<Product>?) {

        DatabaseBuilder.run {
//            getInstance(context!!).productDao().deleteAllProducts()
            getInstance(context!!).productDao().insertProducts(body?.toEntity()!!)
        }
    }
    private suspend fun getProductFromRoom():List<ProductEntity> {

        return DatabaseBuilder.run {
            getInstance(context!!).productDao().getAllProducts()
        }

    }

}