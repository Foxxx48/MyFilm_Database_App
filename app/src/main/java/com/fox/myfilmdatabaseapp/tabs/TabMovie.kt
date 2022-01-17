package com.fox.myfilmdatabaseapp.tabs

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
import com.fox.myfilmdatabaseapp.R
import com.fox.myfilmdatabaseapp.db.Database
import com.fox.myfilmdatabaseapp.databinding.TabProductsBinding
import com.fox.myfilmdatabaseapp.models.MovieModel
import com.fox.myfilmdatabaseapp.repositories.MovieRepository
import com.fox.myfilmdatabaseapp.viewModels.MovieFactory
import com.fox.myfilmdatabaseapp.viewModels.MovieViewModel


class TabMovie : Fragment() {

    private var binding: TabProductsBinding? = null
    private var movieRepository: MovieRepository? = null
    private var movieViewModel: MovieViewModel? = null
    private var movieFactory: MovieFactory? = null
    private var movieAdapter: MovieAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = DataBindingUtil.inflate(inflater, R.layout.tab_movies, container, false)

        val productDao = Database.getInstance((context as FragmentActivity).application).movieDAO
        movieRepository = MovieRepository(productDao)
        movieFactory = MovieFactory(movieRepository!!)
        movieViewModel = ViewModelProvider(this, movieFactory!!).get(MovieViewModel::class.java)
        initRecyclerProducts()

        binding?.deleteAllProducts?.setOnClickListener(View.OnClickListener {
            movieViewModel?.deleteAllMovies()
        })

        return binding?.root
    }

    private fun initRecyclerProducts(){
        binding?.recyclerProducts?.layoutManager = LinearLayoutManager(context)
        movieAdapter = MovieAdapter({ movieModel:MovieModel->deleteProduct(movieModel)},
            { movieModel:MovieModel->editProduct(movieModel)})
        binding?.recyclerProducts?.adapter = movieAdapter

        displayProducts()
    }

    private fun displayProducts(){
        movieViewModel?.movies?.observe(viewLifecycleOwner, Observer {
            movieAdapter?.setList(it)
            movieAdapter?.notifyDataSetChanged()
        })
    }

    private fun deleteProduct(movieModel:MovieModel) {
        movieViewModel?.deleteMovie(movieModel)
    }

    private fun editProduct(movieModel:MovieModel) {
        val panelEditProduct = PanelEditMovie()
        val parameters = Bundle()
        parameters.putString("idProduct", movieModel.id.toString())
        parameters.putString("nameProduct", movieModel.name)
        parameters.putString("categoryProduct", movieModel.category)
        parameters.putString("priceProduct", movieModel.duration)
        panelEditProduct.arguments = parameters

        panelEditProduct.show((context as FragmentActivity).supportFragmentManager, "editProduct")
    }




}