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

        val movieDao = Database.getInstance((context as FragmentActivity).application).movieDAO
        movieRepository = MovieRepository(movieDao)
        movieFactory = MovieFactory(movieRepository!!)
        movieViewModel = ViewModelProvider(this, movieFactory!!).get(MovieViewModel::class.java)
        initRecyclerFilterMovies()



        return binding?.root
    }

    private fun initRecyclerFilterMovies(){
        binding?.recyclerFilter?.layoutManager = LinearLayoutManager(context)
        movieAdapter = MovieAdapter({ movieModel: MovieModel ->deleteMovie(movieModel)},
            { movieModel: MovieModel ->editMovie(movieModel)})
        binding?.recyclerFilter?.adapter = movieAdapter

        displayFilterMovies()
    }

    private fun displayFilterMovies(){
        movieViewModel?.getFilter("Экшн", "137 мин.")?.observe(viewLifecycleOwner, Observer {
            movieAdapter?.setList(it)
            movieAdapter?.notifyDataSetChanged()
        })
    }

    private fun deleteMovie(movieModel:MovieModel) {
        movieViewModel?.deleteMovie(movieModel)
    }

    private fun editMovie(movieModel:MovieModel) {
        val panelEditMovie = PanelEditMovie()
        val parameters = Bundle()
        parameters.putString("idMovie", movieModel.id.toString())
        parameters.putString("nameMovie", movieModel.name)
        parameters.putString("categoryMovie", movieModel.category)
        parameters.putString("durationMovie", movieModel.duration)
        panelEditMovie.arguments = parameters

        panelEditMovie.show((context as FragmentActivity).supportFragmentManager, "editMovie")
    }


}