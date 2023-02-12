package br.com.products.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class Products(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    var title: String,
    var type: String,
    var description: String,
    var filename: String,
    var height: Int,
    var width: Int,
    var price: Double,
    var rating: Int
)
