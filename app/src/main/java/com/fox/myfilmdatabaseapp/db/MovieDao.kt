package com.fox.myfilmdatabaseapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.fox.myfilmdatabaseapp.models.MovieModel


@Dao
interface MovieDao {

    @Insert
    suspend fun insertMovie(movieModel: MovieModel)

    @Update
    suspend fun updateMovie(movieModel: MovieModel)

    @Delete
    suspend fun deleteMovie(movieModel: MovieModel)

    @Query("DELETE FROM movie_data_table")
     suspend fun deleteAllMovies()

    @Query("SELECT * FROM movie_data_table")
     fun getAllMovies(): LiveData<List<MovieModel>>

    @Query("SELECT * FROM movie_data_table WHERE movie_category = :nameCategory AND movie_duration = :movieDuration")
      fun getFilter(nameCategory:String, movieDuration:String): LiveData<List<MovieModel>>

    @Query("SELECT * FROM movie_data_table WHERE movie_category = 'ЭКШН' AND movie_duration = '120'")
     fun getMovie(): LiveData<List<MovieModel>>

    @Query("SELECT * FROM movie_data_table WHERE movie_category = :nameCategory OR movie_duration = :movieDuration")
     fun getThreeVariant(nameCategory:String, movieDuration:String): LiveData<List<MovieModel>>
}