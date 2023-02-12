package br.com.products.presenter.viewmodel

import androidx.lifecycle.*
import br.com.Productss.lista
import br.com.products.data.local.Products
import br.com.products.data.local.ProductsRepository
import kotlinx.coroutines.launch

class ProductsViewModel(
    val repository: ProductsRepository
) : ViewModel() {

    var productsList = MutableLiveData<List<Products>>()

    var product = MutableLiveData<Products>()

    fun getItemByTitle(title: String) {
        viewModelScope.launch {
            val item = repository.getItemByTitle(title)
            product.postValue(item)
        }
    }

    fun getAll() {
        viewModelScope.launch {
            productsList.postValue(repository.getAll())
        }
    }

    fun addAllProducts() {
        viewModelScope.launch {
            lista.forEach {
                repository.insert(it)
                getAll()
            }
        }
    }

    fun update(products: Products) {
        viewModelScope.launch {
            repository.update(products)
        }
    }

    fun remove(position: Int) {
        viewModelScope.launch {
            val products = productsList.value!![position]
            repository.delete(products.id.toString())
            getAll()
        }
    }
}
