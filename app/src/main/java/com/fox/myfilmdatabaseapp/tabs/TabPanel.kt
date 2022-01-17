package com.fox.myfilmdatabaseapp.tabs

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.fox.myfilmdatabaseapp.R
import com.fox.myfilmdatabaseapp.db.Database
import com.fox.myfilmdatabaseapp.databinding.TabPanelBinding
import com.fox.myfilmdatabaseapp.repositories.CategoryRepository
import com.fox.myfilmdatabaseapp.repositories.MovieRepository
import com.fox.myfilmdatabaseapp.viewModels.CategoryFactory
import com.fox.myfilmdatabaseapp.viewModels.CategoryViewModel
import com.fox.myfilmdatabaseapp.viewModels.MovieFactory
import com.fox.myfilmdatabaseapp.viewModels.MovieViewModel


class TabPanel : Fragment(), View.OnClickListener, View.OnKeyListener {

    private var binding:TabPanelBinding? = null
    private var categoryRepository:CategoryRepository? = null
    private var categoryViewModel: CategoryViewModel? = null
    private var categoryFactory: CategoryFactory? = null

    private var movieRepository:MovieRepository? = null
    private var movieViewModel: MovieViewModel? = null
    private var movieFactory: MovieFactory? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = DataBindingUtil.inflate(inflater, R.layout.tab_panel, container, false)

        val categoryDao = Database.getInstance((context as FragmentActivity).application).categoryDAO
        categoryRepository = CategoryRepository(categoryDao)
        categoryFactory = CategoryFactory(categoryRepository!!)
        categoryViewModel = ViewModelProvider(this, categoryFactory!!).get(CategoryViewModel::class.java)

        val movieDao = Database.getInstance((context as FragmentActivity).application).movieDAO
        movieRepository = MovieRepository(movieDao)
        movieFactory = MovieFactory(movieRepository!!)
        movieViewModel = ViewModelProvider(this,movieFactory!!).get(MovieViewModel::class.java)

        binding?.enterNameMovie?.setOnKeyListener(this)
        binding?.enterCategoryProduct?.setOnKeyListener(this)
        binding?.enterPriceProduct?.setOnKeyListener(this)

        binding?.buttonAddProduct?.setOnClickListener(this)

        binding?.buttonAddCategoryClothes?.setOnClickListener(this)
        binding?.buttonAddCategoryShoes?.setOnClickListener(this)
        binding?.buttonAddCategoryAccessories?.setOnClickListener(this)

        return binding?.root
    }


    override fun onKey(view: View, i: Int, keyEvent: KeyEvent): Boolean {
        when (view.id) {

            R.id.enterNameProduct -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                    binding?.resEnterNameProduct?.text = binding?.enterNameMovie?.text
                    binding?.enterNameMovie?.setText("")
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

               categoryViewModel?.startInsert(binding?.buttonAddCategoryClothes?.text?.toString()!!)

            }

            R.id.buttonAddCategoryShoes -> {

                categoryViewModel?.startInsert(binding?.buttonAddCategoryShoes?.text?.toString()!!)

            }

            R.id.buttonAddCategoryAccessories -> {

                categoryViewModel?.startInsert(binding?.buttonAddCategoryAccessories?.text?.toString()!!)

            }

            R.id.buttonAddProduct -> {

                movieViewModel?.startInsert(binding?.resEnterNameProduct?.text?.toString()!!,
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