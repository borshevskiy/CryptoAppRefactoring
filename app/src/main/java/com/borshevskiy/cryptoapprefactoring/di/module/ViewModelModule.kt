package com.borshevskiy.cryptoapprefactoring.di.module

import androidx.lifecycle.ViewModel
import com.borshevskiy.cryptoapprefactoring.di.ViewModelKey
import com.borshevskiy.cryptoapprefactoring.presentation.CoinViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(CoinViewModel::class)
    @Binds
    fun bindCoinViewModel(coinViewModel: CoinViewModel): ViewModel
}