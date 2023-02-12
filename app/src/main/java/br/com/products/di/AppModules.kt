package br.com.products.di

import br.com.products.data.local.ProductsDataBase
import br.com.products.data.local.ProductsRepository
import br.com.products.presenter.viewmodel.ProductsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    viewModel {
        ProductsViewModel(
            repository = get()
        )
    }

    factory {
        ProductsRepository(
           productsDAO = get()
        )
    }

    single {
        ProductsDataBase.getDatabase(androidContext()).productsDao()
    }
}