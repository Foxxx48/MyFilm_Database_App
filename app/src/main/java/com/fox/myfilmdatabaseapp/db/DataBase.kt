package com.fox.myfilmdatabaseapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Categories::class, Products::class],version = 1)

abstract class Database : RoomDatabase() {

    abstract val productsDAO : ProductsDAO
    abstract val categoriesDAO : CategoriesDAO


    companion object{
        @Volatile
        private var INSTANCE : com.fox.myfilmdatabaseapp.Database? = null
        fun getInstance(context: Context):com.fox.myfilmdatabaseapp.Database{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        com.fox.myfilmdatabaseapp.Database::class.java,
                        "database"
                    ).build()
                }
                return instance
            }
        }

    }
}
