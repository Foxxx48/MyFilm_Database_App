package com.fox.myfilmdatabaseapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fox.myfilmdatabaseapp.models.CategoryModel
import com.fox.myfilmdatabaseapp.models.MovieModel


@Database(entities = [CategoryModel::class, MovieModel::class],version = 1)
abstract class Database:RoomDatabase() {

    abstract val movieDAO : MovieDao
    abstract val categoryDAO : CategoryDao

    companion object{
        @Volatile
        private var INSTANCE : com.fox.myfilmdatabaseapp.db.Database? = null
        fun getInstance(context: Context):com.fox.myfilmdatabaseapp.db.Database{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        com.fox.myfilmdatabaseapp.db.Database::class.java,
                        "database"
                    ).build()
                }
                return instance
            }
        }

    }
}