package com.fox.myfilmdatabaseapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductsDAO {

    @Insert
    suspend fun insertProduct(products: Products)

    @Update
    suspend fun updateProduct(products: Products)

    @Delete
    suspend fun deleteProduct(products: Products)

    @Query("DELETE FROM products_data_table")
    suspend fun deleteAllProducts()

    @Query("SELECT * FROM products_data_table")
    fun getAllProducts(): LiveData<List<Products>>

    @Query("SELECT * FROM products_data_table WHERE products_category = :nameCategory AND products_price = :priceProduct")
    fun getFilter(nameCategory:String, priceProduct:String): LiveData<List<Products>>

    @Query("SELECT * FROM products_data_table WHERE products_category = 'Аксессуары' AND products_price = '5000'")
    fun getAccess(): LiveData<List<Products>>

    @Query("SELECT * FROM products_data_table WHERE products_category = :nameCategoryVariantOne OR products_category = :nameCategoryVariantTwo")
    fun getThreeVariant(nameCategoryVariantOne:String, nameCategoryVariantTwo:String): LiveData<List<Products>>
}