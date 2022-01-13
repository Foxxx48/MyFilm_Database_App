package com.fox.myfilmdatabaseapp


class CategoriesRepository(private val categoriesDAO: CategoriesDAO) {

    val categories = categoriesDAO.getAllCategories()

    suspend fun insertCategory(categories: Categories){
        categoriesDAO.insertCategory(categories)
    }

    suspend fun updateCategory(categories: Categories){
        categoriesDAO.updateCategory(categories)
    }

    suspend fun deleteCategory(categories: Categories) {
        return categoriesDAO.deleteCategory(categories)
    }

    suspend fun deleteAllCategories(){
        categoriesDAO.deleteAllCategories()
    }
}


