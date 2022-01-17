package com.fox.myfilmdatabaseapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_data_table")
data class MovieModel (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "movie_id")
    var id : Int,

    @ColumnInfo(name = "movie_name")
    var name : String,

    @ColumnInfo(name = "movie_category")
    var category : String,

    @ColumnInfo(name = "movie_duration")
    var duration : String

)