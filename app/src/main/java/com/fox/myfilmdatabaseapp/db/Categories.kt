package com.fox.myfilmdatabaseapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories_data_table")
data class Categories (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "categories_id")
    var id : Int,

    @ColumnInfo(name = "categories_name")
    var name : String

)


