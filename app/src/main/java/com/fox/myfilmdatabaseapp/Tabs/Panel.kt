package com.fox.myfilmdatabaseapp.Tabs

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.fox.myfilmdatabaseapp.CategoriesRepository
import com.fox.myfilmdatabaseapp.Database
import com.fox.myfilmdatabaseapp.ProductsRepository
import com.fox.myfilmdatabaseapp.R
import com.fox.myfilmdatabaseapp.Tabs.categories.CategoriesViewModel
import com.fox.myfilmdatabaseapp.Tabs.categories.CategoriesViewModelFactory
import com.fox.myfilmdatabaseapp.databinding.PanelBinding
import com.fox.myfilmdatabaseapp.room.Tabs.products.ProductsViewModel
import com.fox.myfilmdatabaseapp.room.Tabs.products.ProductsViewModelFactory


class Panel : Fragment(),View.OnKeyListener,View.OnClickListener {

    private var binding: PanelBinding? = null
    private var categoriesRepository: CategoriesRepository? = null
    private var categoriesViewModel: CategoriesViewModel? = null

    private var productsRepository: ProductsRepository? = null
    private var productsViewModel: ProductsViewModel? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.panel, container, false)

        val categoriesDao = Database.getInstance((context as FragmentActivity).application).categoriesDAO
        categoriesRepository = CategoriesRepository(categoriesDao)
        val factory = CategoriesViewModelFactory(categoriesRepository!!)
        categoriesViewModel = ViewModelProvider(this,factory).get(CategoriesViewModel::class.java)

        val productsDao = Database.getInstance((context as FragmentActivity).application).productsDAO
        productsRepository = ProductsRepository(productsDao)
        val factoryProducts = ProductsViewModelFactory(productsRepository!!)
        productsViewModel = ViewModelProvider(this,factoryProducts).get(ProductsViewModel::class.java)

        binding?.enterNameProduct?.setOnKeyListener(this)
        binding?.enterCategoryProduct?.setOnKeyListener(this)
        binding?.enterPriceProduct?.setOnKeyListener(this)

        binding?.buttonAddCategoryClothes?.setOnClickListener(this)
        binding?.buttonAddCategoryShoes?.setOnClickListener(this)
        binding?.buttonAddCategoryAccessories?.setOnClickListener(this)
        binding?.buttonAddProduct?.setOnClickListener(this)



        return binding?.root
    }

    override fun onKey(view: View, i: Int, keyEvent: KeyEvent): Boolean {
        when (view.id) {

            R.id.enterNameProduct -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                    binding?.resEnterNameProduct?.text = binding?.enterNameProduct?.text
                    binding?.enterNameProduct?.setText("")
                    return true
                }

            }

            R.id.enterCategoryProduct -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                    binding?.resEnterCategoryProduct?.text = binding?.enterCategoryProduct?.text
                    binding?.enterCategoryProduct?.setText("")
                    return true
                }

            }

            R.id.enterPriceProduct -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                    binding?.resEnterPriceProduct?.text = binding?.enterPriceProduct?.text
                    binding?.enterPriceProduct?.setText("")
                    return true
                }

            }
        }

        return false
    }

    override fun onClick(view: View) {

        when(view.id) {

            R.id.buttonAddCategoryClothes -> {

                categoriesViewModel?.startInsert(binding?.buttonAddCategoryClothes?.text?.toString()!!)

            }

            R.id.buttonAddCategoryShoes -> {

                categoriesViewModel?.startInsert(binding?.buttonAddCategoryShoes?.text?.toString()!!)

            }

            R.id.buttonAddCategoryAccessories -> {

                categoriesViewModel?.startInsert(binding?.buttonAddCategoryAccessories?.text?.toString()!!)

            }

            R.id.buttonAddProduct -> {

                productsViewModel?.startInsert(binding?.resEnterNameProduct?.text?.toString()!!,
                    binding?.resEnterCategoryProduct?.text?.toString()!!,
                    binding?.resEnterPriceProduct?.text?.toString()!!)

                clearResEnterProduct()

            }
        }

    }

    private fun clearResEnterProduct() {
        binding?.resEnterNameProduct?.setText("")
        binding?.resEnterCategoryProduct?.setText("")
        binding?.resEnterPriceProduct?.setText("")

    }

}