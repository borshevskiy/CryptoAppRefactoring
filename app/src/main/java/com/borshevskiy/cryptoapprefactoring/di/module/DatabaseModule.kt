package com.borshevskiy.cryptoapprefactoring.di.module

import android.content.Context
import androidx.room.Room
import com.borshevskiy.cryptoapprefactoring.data.database.AppDatabase
import com.borshevskiy.cryptoapprefactoring.data.database.CoinInfoDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(context: Context) = Room.databaseBuilder(context, AppDatabase::class.java, "main.db").build()

    @Singleton
    @Provides
    fun provideDAO(appDatabase: AppDatabase): CoinInfoDao {
        return appDatabase.coinPriceInfoDao()
    }

}