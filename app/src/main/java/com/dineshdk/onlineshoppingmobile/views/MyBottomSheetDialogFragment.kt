package com.dineshdk.onlineshoppingmobile.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dineshdk.onlineshoppingmobile.databinding.BottomSheetBinding
import com.dineshdk.onlineshoppingmobile.models.ProductEntity
import com.dineshdk.onlineshoppingmobile.models.ShopViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MyBottomSheetDialogFragment(val clickListener: ItemClickListener, var product: ProductEntity)
    : BottomSheetDialogFragment(), StorageAdapter.ItemClickListener,
    ColorAdapter.ColorItemClickListener {


    private var _binding: BottomSheetBinding? = null
    private val binding get() = _binding!!

    private var shopViewModel: ShopViewModel? = null

    private lateinit var colorAdapter: ColorAdapter
    private lateinit var storageAdapter: StorageAdapter
    lateinit var color:String
    lateinit var storage:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomSheetBinding.inflate(inflater, container, false)

        shopViewModel = ViewModelProvider(requireActivity()).get(ShopViewModel::class.java)

        // Set up color options in RecyclerView
        colorAdapter = ColorAdapter(getColorOptions(),this)
        binding.colorRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.colorRecyclerView.adapter = colorAdapter

        // Set up storage options in RecyclerView
        storageAdapter = StorageAdapter(getStorageOptions(),this)
        binding.storageRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.storageRecyclerView.adapter = storageAdapter

        binding.product = shopViewModel?.getProduct()
        color = shopViewModel?.getProduct()?.colorOptions?.get(0) ?: ""
        storage = shopViewModel?.getProduct()?.storageOptions?.get(0) ?: ""

        binding.closeIcon.setOnClickListener { dismiss() }
        binding.cancelButton.setOnClickListener { dismiss() }
        binding.applyButton.setOnClickListener {
            // Handle apply action
            clickListener.onItemClick(color,storage)
            dismiss()
        }

        return binding.root
    }

    override fun onColorItemClick(c: String) {
        binding.selectedColor.text = c
        color = c
    }

    override fun onStorageItemClick(s: String) {
        binding.selectedStorage.text = s
        storage = s
    }

    private fun getColorOptions() = product.colorOptions

    private fun getStorageOptions() = product.storageOptions


    public interface ItemClickListener{
        public fun onItemClick(c:String,s:String)
    }

}
