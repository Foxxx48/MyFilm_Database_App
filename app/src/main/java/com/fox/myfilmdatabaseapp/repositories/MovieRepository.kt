package com.fox.myfilmdatabaseapp.repositories

import androidx.lifecycle.LiveData
import com.fox.myfilmdatabaseapp.db.MovieDao
import com.fox.myfilmdatabaseapp.models.MovieModel


class MovieRepository (private val movieDAO: MovieDao) {

    val movies = movieDAO.getAllMovies()

    fun getFilter (nameCategory:String, durationMovie:String):
            LiveData<List<MovieModel>> {
        return movieDAO.getFilter(nameCategory, durationMovie)
    }


    suspend fun insertMovie(movieModel: MovieModel){
        movieDAO.insertMovie(movieModel)
    }

    suspend fun updateMovie(movieModel: MovieModel){
       movieDAO.updateMovie(movieModel)
    }

    suspend fun deleteMovie(movieModel: MovieModel) {
        movieDAO.deleteMovie(movieModel)
    }

    suspend fun deleteAllMovies(){
        movieDAO.deleteAllMovies()
    }
}
