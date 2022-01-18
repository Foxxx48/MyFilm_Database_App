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
        binding?.enterCategoryMovie?.setOnKeyListener(this)
        binding?.enterDurationMovie?.setOnKeyListener(this)

        binding?.buttonAddMovie?.setOnClickListener(this)

        binding?.buttonAddCategoryMovies?.setOnClickListener(this)
        binding?.buttonAddCategoryAction?.setOnClickListener(this)
        binding?.buttonAddCategoryAnimation?.setOnClickListener(this)

        return binding?.root
    }


    override fun onKey(view: View, i: Int, keyEvent: KeyEvent): Boolean {
        when (view.id) {

            R.id.enter_name_movie -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                    binding?.resEnterNameMovie?.text = binding?.enterNameMovie?.text
                    binding?.enterNameMovie?.setText("")
                    return true
                }

            }

            R.id.enter_category_movie -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                    binding?.resEnterCategoryMovie?.text = binding?.enterCategoryMovie?.text
                    binding?.enterCategoryMovie?.setText("")
                    return true
                }

            }

            R.id.enter_duration_movie -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                    binding?.resEnterDurationMovie?.text = binding?.enterDurationMovie?.text
                    binding?.enterDurationMovie?.setText("")
                    return true
                }

            }
        }

        return false
    }

    override fun onClick(view: View) {

        when(view.id) {

            R.id.button_add_category_movies -> {

               categoryViewModel?.startInsert(binding?.buttonAddCategoryMovies?.text?.toString()!!)

            }

            R.id.`@+id/button_add_category_action` -> {

                categoryViewModel?.startInsert(binding?.buttonAddCategoryAction?.text?.toString()!!)

            }

            R.id.`@+id/button_add_category_animation` -> {

                categoryViewModel?.startInsert(binding?.buttonAddCategoryAnimation?.text?.toString()!!)

            }

            R.id.`@+id/button_add_movie` -> {

                movieViewModel?.startInsert(binding?.resEnterNameMovie?.text?.toString()!!,
                    binding?.resEnterCategoryMovie?.text?.toString()!!,
                    binding?.resEnterDurationMovie?.text?.toString()!!)

                clearResEnterMovie()

            }
        }

    }

    private fun clearResEnterMovie() {
        binding?.resEnterNameMovie?.setText("")
        binding?.resEnterCategoryMovie?.setText("")
        binding?.resEnterDurationMovie?.setText("")

    }


}