package com.fox.myfilmdatabaseapp.Tabs.categories
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
import com.fox.myfilmdatabaseapp.Categories
import com.fox.myfilmdatabaseapp.CategoriesRepository
import com.fox.myfilmdatabaseapp.Database
import com.fox.myfilmdatabaseapp.R
import com.fox.myfilmdatabaseapp.databinding.CatalogCategoriesBinding



class CatalogCategories : Fragment(),View.OnClickListener {

    private var binding:CatalogCategoriesBinding? = null
    private var categoriesRepository: CategoriesRepository? = null
    private var categoriesViewModel: CategoriesViewModel? = null
    private var categoriesViewModelFactory: CategoriesViewModelFactory? = null
    private var categoriesAdapter: CategoriesAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.catalog_categories, container, false)


        val categoriesDao = Database.getInstance((context as FragmentActivity).application).categoriesDAO
        categoriesRepository = CategoriesRepository(categoriesDao)
        categoriesViewModelFactory = CategoriesViewModelFactory(categoriesRepository!!)
        categoriesViewModel = ViewModelProvider(this, categoriesViewModelFactory!!).get(CategoriesViewModel::class.java)
        initRecyclerCategories()

        binding?.deleteAllCategories?.setOnClickListener(this)

        return binding?.root
    }


    private fun initRecyclerCategories(){
        binding?.recyclerCategories?.layoutManager = LinearLayoutManager(context)
        categoriesAdapter = CategoriesAdapter({categories: Categories ->deleteCategory(categories)},
            {categories:Categories->editCategory(categories)})
        binding?.recyclerCategories?.adapter = categoriesAdapter

        displayCategories()
    }

    private fun displayCategories(){
        categoriesViewModel?.categories?.observe(viewLifecycleOwner, Observer {
            categoriesAdapter?.setList(it)
            categoriesAdapter?.notifyDataSetChanged()
        })
    }

    override fun onClick(v: View?) {
        categoriesViewModel?.deleteAll()
    }

    private fun deleteCategory(categories: Categories) {
        categoriesViewModel?.delete(categories)
    }

    private fun editCategory(categories: Categories) {
        val panelCategory = PanelEditCategory()
        val parameters = Bundle()
        parameters.putString("idCategory", categories.id.toString())
        parameters.putString("nameCategory", categories.name)
        panelCategory.arguments = parameters

        panelCategory.show((context as FragmentActivity).supportFragmentManager, "editCategory")
    }

}