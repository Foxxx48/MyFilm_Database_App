package com.fox.myfilmdatabaseapp.Tabs.categories

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.fox.myfilmdatabaseapp.CategoriesRepository
import com.fox.myfilmdatabaseapp.Database
import com.fox.myfilmdatabaseapp.R
import com.fox.myfilmdatabaseapp.databinding.PanelEditCategoryBinding

import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class PanelEditCategory : BottomSheetDialogFragment(),View.OnKeyListener {

    private var binding: PanelEditCategoryBinding? = null
    private var categoriesRepository: CategoriesRepository? = null
    private var categoriesViewModel: CategoriesViewModel? = null
    private var idCategory:Int? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.panel_edit_category, container, false)

        idCategory = arguments?.getString("idCategory")?.toInt()
        binding?.editCategory?.setText(arguments?.getString("nameCategory").toString())

        val categoriesDao = Database.getInstance((context as FragmentActivity).application).categoriesDAO
        categoriesRepository = CategoriesRepository(categoriesDao)
        val factory = CategoriesViewModelFactory(categoriesRepository!!)
        categoriesViewModel = ViewModelProvider(this,factory).get(CategoriesViewModel::class.java)

        binding?.editCategory?.setOnKeyListener(this)

        return binding?.root
    }

    override fun onKey(view: View, i: Int, keyEvent: KeyEvent): Boolean {
        when (view.id) {


            R.id.editCategory -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {

                    categoriesViewModel?.startUpdateProduct(idCategory.toString().toInt(), binding?.editCategory?.text?.toString()!!)

                    binding?.editCategory?.setText("")

                    dismiss()

                    (context as FragmentActivity).supportFragmentManager.beginTransaction().replace(R.id.content, CatalogCategories()).commit()

                    return true
                }

            }
        }

        return false
    }

}