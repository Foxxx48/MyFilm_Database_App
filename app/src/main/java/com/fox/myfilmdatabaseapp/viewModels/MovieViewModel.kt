package com.fox.myfilmdatabaseapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.fox.myfilmdatabaseapp.models.MovieModel
import com.fox.myfilmdatabaseapp.repositories.MovieRepository

class MovieViewModel (private val movieRepository: MovieRepository) : ViewModel() {

    val movies = movieRepository.movies


    fun getFilter (nameCategory:String, durationMovie:String):
            LiveData<List<MovieModel>> {
        return movieRepository.getFilter(nameCategory, durationMovie)
    }

    fun startInsert(nameMovie:String, categoryMovie:String, durationMovie:String) {
        insertMovie(MovieModel(0,nameMovie, categoryMovie, durationMovie))
    }

    fun startUpdateProduct(idMovie:Int, nameMovie:String, nameCategory:String, durationMovie:String) {
        updateMovie(MovieModel(idMovie, nameMovie, nameCategory, durationMovie))
    }

    fun insertMovie(movieModel: MovieModel) = viewModelScope.launch{

        movieRepository.insertMovie(movieModel)
    }

    fun updateMovie(movieModel: MovieModel) = viewModelScope.launch{


        movieRepository.updateMovie(movieModel)
    }

    fun deleteMovie(movieModel: MovieModel) = viewModelScope.launch{

        movieRepository.deleteMovie(movieModel)
    }

    fun deleteAllMovies() = viewModelScope.launch{

        movieRepository.deleteAllMovies()
    }


}