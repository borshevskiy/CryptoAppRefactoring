package com.borshevskiy.cryptoapprefactoring.di.components

import android.app.Application
import android.content.Context
import androidx.room.Database
import com.borshevskiy.cryptoapprefactoring.CryptoApp
import com.borshevskiy.cryptoapprefactoring.di.module.DataModule
import com.borshevskiy.cryptoapprefactoring.di.module.DatabaseModule
import com.borshevskiy.cryptoapprefactoring.di.module.NetworkModule
import com.borshevskiy.cryptoapprefactoring.di.module.ViewModelModule
import com.borshevskiy.cryptoapprefactoring.presentation.CoinDetailFragment
import com.borshevskiy.cryptoapprefactoring.presentation.CoinPriceListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, ViewModelModule::class, DatabaseModule::class, NetworkModule::class])
interface AppComponent {

    fun inject(coinDetailFragment: CoinDetailFragment)

    fun inject(coinPriceListFragment: CoinPriceListFragment)

    fun inject(application: CryptoApp)

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): AppComponent

    }


}