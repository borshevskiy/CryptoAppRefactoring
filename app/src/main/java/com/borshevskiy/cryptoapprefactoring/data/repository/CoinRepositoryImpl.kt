package com.borshevskiy.cryptoapprefactoring.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.borshevskiy.cryptoapprefactoring.data.database.CoinInfoDao
import com.borshevskiy.cryptoapprefactoring.data.mapper.CoinMapper
import com.borshevskiy.cryptoapprefactoring.data.workers.RefreshDataWorker
import com.borshevskiy.cryptoapprefactoring.domain.CoinInfo
import com.borshevskiy.cryptoapprefactoring.domain.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val mapper: CoinMapper,
    private val coinInfoDao: CoinInfoDao,
    private val context: Context
) : CoinRepository {

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        return Transformations.map(coinInfoDao.getPriceList()) {
            it.map { mapper.mapDbModelToEntity(it) }
        }
    }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> {
        return Transformations.map(coinInfoDao.getPriceInfoAboutCoin(fromSymbol)) {
            mapper.mapDbModelToEntity(it)
        }
    }

    override fun loadData() {
        val workManager = WorkManager.getInstance(context)
        workManager.enqueueUniqueWork(RefreshDataWorker.NAME,ExistingWorkPolicy.REPLACE, RefreshDataWorker.makeRequest())
    }
}