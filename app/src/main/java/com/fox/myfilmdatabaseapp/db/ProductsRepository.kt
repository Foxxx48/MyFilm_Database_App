package com.fox.myfilmdatabaseapp

import androidx.lifecycle.LiveData

class ProductsRepository(private val productsDAO: ProductsDAO) {

    val products = productsDAO.getAllProducts()

    fun getFilter (nameCategory:String, priceProduct:String):
            LiveData<List<Products>> {
        return productsDAO.getFilter(nameCategory, priceProduct)
    }


    suspend fun insertProduct(products: Products){
        productsDAO.insertProduct(products)
    }

    suspend fun updateProduct(products: Products){
        productsDAO.updateProduct(products)
    }

    suspend fun deleteProduct(products: Products) {
        productsDAO.deleteProduct(products)
    }

    suspend fun deleteAllProducts(){
        productsDAO.deleteAllProducts()
    }
}
