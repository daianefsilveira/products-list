package br.com.products.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Products::class], version = 1)
abstract class ProductsDataBase : RoomDatabase() {
    abstract fun productsDao(): ProductsDAO

    companion object {

        private var instance: ProductsDataBase? = null

        fun getDatabase(context: Context): ProductsDataBase {
            if (instance == null) {
                synchronized(ProductsDataBase::class.java) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ProductsDataBase::class.java,
                        "transition_database"
                    ).build()
                }
            }
            return instance!!
        }
    }
}