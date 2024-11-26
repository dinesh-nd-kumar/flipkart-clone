package com.dineshdk.onlineshoppingmobile.models

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dineshdk.onlineshoppingmobile.repo.ShopRepo
import kotlinx.coroutines.launch

class ShopViewModel : ViewModel() {

    private val repo = ShopRepo()

    private var mutableProduct : ProductEntity? = null


    fun loadData(context:Context) {
        viewModelScope.launch{
            repo.loadData(context)
        }
    }
    fun getProductLiveData() : LiveData<List<ProductEntity>> {
        return repo.getData()
    }

    fun setProduct(product: ProductEntity) {
        mutableProduct = product
    }

    fun getProduct(): ProductEntity?{
        return mutableProduct
    }


}