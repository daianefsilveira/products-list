package br.com.products

import android.app.Application
import br.com.products.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidContext(this@AppApplication)
            modules(appModules)
        }
    }
}