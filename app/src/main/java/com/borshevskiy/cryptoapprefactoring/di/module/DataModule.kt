package com.borshevskiy.cryptoapprefactoring.di.module

import com.borshevskiy.cryptoapprefactoring.data.repository.CoinRepositoryImpl
import com.borshevskiy.cryptoapprefactoring.domain.CoinRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataModule {

    @Singleton
    @Binds
    fun bindCoinRepository(coinRepositoryImpl: CoinRepositoryImpl): CoinRepository
}