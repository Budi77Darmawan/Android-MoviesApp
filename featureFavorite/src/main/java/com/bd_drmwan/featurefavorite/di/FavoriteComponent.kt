package com.bd_drmwan.featurefavorite.di

import android.content.Context
import com.bd_drmwan.featurefavorite.view.ListFavoriteFragment
import com.bd_drmwan.moviesapp.modules.FavoriteModuleDependencies
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoriteModuleDependencies::class])
interface FavoriteComponent {

    fun inject(fragment: ListFavoriteFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModuleDependencies: FavoriteModuleDependencies): Builder
        fun build(): FavoriteComponent
    }
}