package com.fox.myfilmdatabaseapp.Tabs.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.fox.myfilmdatabaseapp.Categories
import com.fox.myfilmdatabaseapp.CategoriesRepository

import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class  CategoriesViewModel(private val categoriesRepository: CategoriesRepository) : ViewModel() {

    val categories = categoriesRepository.categories

    fun startInsert(nameCategories:String) {
        insert(Categories(0, nameCategories))
    }

    fun startUpdateProduct(idCategories:Int, nameCategories:String) {
        updateProduct(Categories(idCategories, nameCategories))
    }

    fun insert(categories: Categories) = viewModelScope.launch{

        categoriesRepository.insertCategory(categories)
    }

    fun updateProduct(categories: Categories) = viewModelScope.launch{

        categoriesRepository.updateCategory(categories)
    }

    fun delete(categories: Categories) = viewModelScope.launch{

        categoriesRepository.deleteCategory(categories)
    }

    fun deleteAll() = viewModelScope.launch{

        categoriesRepository.deleteAllCategories()
    }


}