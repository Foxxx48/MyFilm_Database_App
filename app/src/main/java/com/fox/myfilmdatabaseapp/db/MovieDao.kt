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
     fun deleteAllMovies()

    @Query("SELECT * FROM movie_data_table")
    fun getAllMovies(): LiveData<List<MovieModel>>

    @Query("SELECT * FROM movie_data_table WHERE movie_category = :nameCategory AND movie_duration = :movieDuration")
    fun getFilter(nameCategory:String, priceProduct:String): LiveData<List<MovieModel>>

    @Query("SELECT * FROM movie_data_table WHERE movie_category = 'Одежда' AND movie_duration = '2000'")
    fun getClothes(): LiveData<List<MovieModel>>

    @Query("SELECT * FROM movie_data_table WHERE movie_category = :nameCategory OR movie_duration = :price")
    fun getThreeVariant(nameCategory:String, price:String): LiveData<List<MovieModel>>
}