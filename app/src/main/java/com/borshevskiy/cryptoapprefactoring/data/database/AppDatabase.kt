package com.borshevskiy.cryptoapprefactoring.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CoinInfoDbModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun coinPriceInfoDao(): CoinInfoDao
}
