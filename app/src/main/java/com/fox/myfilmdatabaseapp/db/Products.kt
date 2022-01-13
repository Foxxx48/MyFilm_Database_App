package com.fox.myfilmdatabaseapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products_data_table")
data class Products (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "products_id")
    var id : Int,

    @ColumnInfo(name = "products_name")
    var name : String,

    @ColumnInfo(name = "products_category")
    var category : String,

    @ColumnInfo(name = "products_price")
    var price : String

)