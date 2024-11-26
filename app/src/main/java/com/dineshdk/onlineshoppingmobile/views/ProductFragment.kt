package com.dineshdk.onlineshoppingmobile.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dineshdk.onlineshoppingmobile.databinding.FragmentProductBinding
import com.dineshdk.onlineshoppingmobile.models.ProductEntity
import com.dineshdk.onlineshoppingmobile.models.ShopViewModel


class ProductFragment : Fragment(), MyBottomSheetDialogFragment.ItemClickListener {

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

    private var shopViewModel: ShopViewModel? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shopViewModel = ViewModelProvider(requireActivity()).get(ShopViewModel::class.java)

        binding.product = shopViewModel?.getProduct()
        binding.colorRow.setOnClickListener {
            showVariantBottomSheet()
        }
        binding.storageRow.setOnClickListener {
            showVariantBottomSheet()
        }



    }
    override fun onItemClick(c:String,s:String) {
        binding.colorValue.text = c
        binding.storageValue.text = s


    }

    private fun showVariantBottomSheet() {
        val bottomSheet = MyBottomSheetDialogFragment(this,shopViewModel?.getProduct()!!)
        bottomSheet.show(childFragmentManager, bottomSheet.tag)
    }


}