package br.com.products.data.local

class ProductsRepository(
    private val productsDAO: ProductsDAO
) {
    suspend fun getItemByTitle(title: String): Products {
        return productsDAO.getItemByTitle(title)
    }

    suspend fun insert(products: Products) {
        productsDAO.insert(products)
    }

    suspend fun getAll(): List<Products> {
        return productsDAO.getAll()
    }

    suspend fun update(products: Products) {
        productsDAO.update(products)
    }

    suspend fun delete(id: String) {
        productsDAO.delete(id)
    }
}
