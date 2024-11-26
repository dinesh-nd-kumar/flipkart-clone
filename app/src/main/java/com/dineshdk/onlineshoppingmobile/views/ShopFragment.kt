package com.dineshdk.onlineshoppingmobile.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.dineshdk.onlineshoppingmobile.models.ShopViewModel
import com.dineshdk.onlineshoppingmobile.models.ProductEntity
import com.dineshdk.onlineshoppingmobile.others.ProductAdapter
import com.dineshdk.onlineshoppingmobile.R
import com.dineshdk.onlineshoppingmobile.databinding.FragmentShopBinding
import com.dineshdk.onlineshoppingmobile.models.Product

class ShopFragment : Fragment(), ProductAdapter.ItemClickListener {

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!
    private var shopViewModel: ShopViewModel? = null

    private lateinit var productAdapter: ProductAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()

        shopViewModel = ViewModelProvider(requireActivity()).get(ShopViewModel::class.java)
//        shopViewModel!!.loadData(requireContext())
        shopViewModel!!.getProductLiveData().observe(viewLifecycleOwner){
            setRecycler(it)

        }

    }
    private fun setRecycler(list: List<ProductEntity>?){
        productAdapter.productList = list
        productAdapter.notifyDataSetChanged()
    }
    private fun initRecycler(){
        binding.rvProducts.apply {
            productAdapter = ProductAdapter(this@ShopFragment,null)
            adapter = productAdapter
//            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL))
//            addItemDecoration(DividerItemDecoration( context,DividerItemDecoration.VERTICAL))

        }
    }



    override fun onItemClick(p: ProductEntity) {
        shopViewModel!!.setProduct(p)
        findNavController().navigate(R.id.action_shopFragment_to_productFragment)

    }
}