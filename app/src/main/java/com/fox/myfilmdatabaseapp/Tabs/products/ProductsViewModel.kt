package com.fox.myfilmdatabaseapp.room.Tabs.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fox.myfilmdatabaseapp.Products
import com.fox.myfilmdatabaseapp.ProductsRepository

import kotlinx.coroutines.launch

class ProductsViewModel(private val productsRepository: ProductsRepository) : ViewModel() {

    val products = productsRepository.products


    fun getFilter (nameCategory:String, priceProduct:String):
            LiveData<List<Products>> {
        return productsRepository.getFilter(nameCategory, priceProduct)
    }

    fun startInsert(nameProduct:String, categoryProduct:String, priceProduct:String) {
        insertProduct(Products(0,nameProduct, categoryProduct, priceProduct))
    }

    fun startUpdateProduct(idProduct:Int, nameProduct:String, nameCategory:String, priceProduct:String) {
        updateProduct(Products(idProduct, nameProduct, nameCategory, priceProduct))
    }

    fun insertProduct(products: Products) = viewModelScope.launch{

        productsRepository.insertProduct(products)
    }

    fun updateProduct(products: Products) = viewModelScope.launch{

        productsRepository.updateProduct(products)
    }

    fun deleteProduct(products: Products) = viewModelScope.launch{

        productsRepository.deleteProduct(products)
    }

    fun deleteAllProducts() = viewModelScope.launch{

        productsRepository.deleteAllProducts()
    }


}