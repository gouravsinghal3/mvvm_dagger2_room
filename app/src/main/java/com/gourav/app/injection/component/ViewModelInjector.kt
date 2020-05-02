package com.gourav.app.injection.component

import android.app.Application
import com.gourav.app.injection.module.NetworkModule
import com.gourav.app.ui.cars.ArticleListViewModel
import com.gourav.app.ui.cars.ArticleViewModel
import dagger.BindsInstance
import dagger.Component

import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified ArticleListViewModel.
     * @param articleListViewModel ArticleListViewModel in which to inject the dependencies
     */
    fun inject(articleListViewModel: ArticleListViewModel)
    /**
     * Injects required dependencies into the specified ArticleViewModel.
     * @param articleViewModel ArticleViewModel in which to inject the dependencies
     */
    fun inject(articleViewModel: ArticleViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector
        fun networkModule(networkModule: NetworkModule): Builder
    }
}