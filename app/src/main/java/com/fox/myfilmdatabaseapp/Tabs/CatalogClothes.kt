package com.fox.myfilmdatabaseapp.Tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fox.myfilmdatabaseapp.Database
import com.fox.myfilmdatabaseapp.Products
import com.fox.myfilmdatabaseapp.ProductsRepository
import com.fox.myfilmdatabaseapp.R
import com.fox.myfilmdatabaseapp.Tabs.products.PanelEditProduct
import com.fox.myfilmdatabaseapp.Tabs.products.ProductsAdapter
import com.fox.myfilmdatabaseapp.databinding.CatalogClothesBinding
import com.fox.myfilmdatabaseapp.room.Tabs.products.ProductsViewModel
import com.fox.myfilmdatabaseapp.room.Tabs.products.ProductsViewModelFactory


class CatalogClothes : Fragment() {

    private var binding: CatalogClothesBinding? = null
    private var productsRepository: ProductsRepository? = null
    private var productsViewModel: ProductsViewModel? = null
    private var productsViewModelFactory: ProductsViewModelFactory? = null
    private var productsAdapter: ProductsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.catalog_clothes, container, false)

        val productsDao =
            Database.getInstance((context as FragmentActivity).application).productsDAO
        productsRepository = ProductsRepository(productsDao)
        productsViewModelFactory = ProductsViewModelFactory(productsRepository!!)
        productsViewModel =
            ViewModelProvider(this, productsViewModelFactory!!).get(ProductsViewModel::class.java)
        initRecyclerProducts()

        return binding?.root
    }

    private fun initRecyclerProducts(){
        binding?.recyclerClothes?.layoutManager = LinearLayoutManager(context)
        productsAdapter = ProductsAdapter({products: Products ->deleteProduct(products)},
            {products: Products ->editProduct(products)})
        binding?.recyclerClothes?.adapter = productsAdapter

        displayProducts()
    }

    private fun displayProducts(){
        productsViewModel?.getFilter("одежда", "5000")?.observe(viewLifecycleOwner, Observer {
            productsAdapter?.setList(it)
            productsAdapter?.notifyDataSetChanged()
        })
    }

    private fun deleteProduct(products: Products) {
        productsViewModel?.deleteProduct(products)
    }

    private fun editProduct(products: Products) {
        val panelEditProduct = PanelEditProduct()
        val parameters = Bundle()
        parameters.putString("idProduct", products.id.toString())
        parameters.putString("nameProduct", products.name)
        parameters.putString("categoryProduct", products.category)
        parameters.putString("priceProduct", products.price)
        panelEditProduct.arguments = parameters

        panelEditProduct.show((context as FragmentActivity).supportFragmentManager, "editProduct")
    }

}