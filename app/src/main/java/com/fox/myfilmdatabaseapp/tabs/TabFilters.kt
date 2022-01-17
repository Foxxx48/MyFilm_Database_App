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
import com.fox.myfilmdatabaseapp.databinding.TabFiltersBinding
import com.fox.myfilmdatabaseapp.models.MovieModel
import com.fox.myfilmdatabaseapp.repositories.MovieRepository
import com.fox.myfilmdatabaseapp.viewModels.MovieFactory
import com.fox.myfilmdatabaseapp.viewModels.MovieViewModel

class TabFilters : Fragment() {

    private var binding: TabFiltersBinding? = null
    private var movieRepository: MovieRepository? = null
    private var movieViewModel: MovieViewModel? = null
    private var movieFactory: MovieFactory? = null
    private var movieAdapter: MovieAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.tab_filters, container, false)

        val productDao = Database.getInstance((context as FragmentActivity).application).movieDAO
        movieRepository = MovieRepository(productDao)
        movieFactory = MovieFactory(movieRepository!!)
        movieViewModel = ViewModelProvider(this, movieFactory!!).get(MovieViewModel::class.java)
        initRecyclerFilterProducts()



        return binding?.root
    }

    private fun initRecyclerFilterProducts(){
        binding?.recyclerFilter?.layoutManager = LinearLayoutManager(context)
        movieAdapter = MovieAdapter({ movieModel: MovieModel ->deleteProduct(movieModel)},
            { movieModel: MovieModel ->editProduct(movieModel)})
        binding?.recyclerFilter?.adapter = movieAdapter

        displayFilterProducts()
    }

    private fun displayFilterProducts(){
        movieViewModel?.getFilter("одежда", "5000")?.observe(viewLifecycleOwner, Observer {
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
        parameters.putString("priceProduct", movieModel.price)
        panelEditProduct.arguments = parameters

        panelEditProduct.show((context as FragmentActivity).supportFragmentManager, "editProduct")
    }


}