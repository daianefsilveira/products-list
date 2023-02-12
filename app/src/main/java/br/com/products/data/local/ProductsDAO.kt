package br.com.products.data.local

import androidx.room.*


@Dao
interface ProductsDAO {
    @Update
    suspend fun update(products: Products)

    @Query("DELETE FROM product_table WHERE id = :id")
    suspend fun delete(id: String)

    @Query("SELECT * FROM product_table")
    suspend fun getAll(): List<Products>

    @Query("SELECT * FROM product_table WHERE title =:title LIMIT 1")
    suspend fun getItemByTitle(title: String) : Products

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(products: Products)
}

