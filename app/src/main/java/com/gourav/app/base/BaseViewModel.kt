package com.gourav.app.base

import android.arch.lifecycle.ViewModel
import com.gourav.app.injection.component.DaggerViewModelInjector
import com.gourav.app.injection.component.ViewModelInjector
import com.gourav.app.injection.module.NetworkModule
import com.gourav.app.ui.cars.ArticleListViewModel
import com.gourav.app.ui.cars.ArticleViewModel

abstract class BaseViewModel(): ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is ArticleListViewModel -> injector.inject(this)
            is ArticleViewModel -> injector.inject(this)
        }
    }
}