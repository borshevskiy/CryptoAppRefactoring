package com.borshevskiy.cryptoapprefactoring.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.borshevskiy.cryptoapprefactoring.data.database.CoinInfoDao
import com.borshevskiy.cryptoapprefactoring.data.mapper.CoinMapper
import com.borshevskiy.cryptoapprefactoring.data.network.ApiService
import kotlinx.coroutines.delay

class RefreshDataWorker(
    private val coinInfoDao: CoinInfoDao,
    private val coinMapper: CoinMapper,
    private val apiService: ApiService,
    context: Context,
    workerParameters: WorkerParameters
): CoroutineWorker(context, workerParameters) {


    override suspend fun doWork(): Result {
        while (true) {
            try {
                val topCoins = apiService.getTopCoinsInfo(limit = 50)
                val fSyms = coinMapper.mapNamesListToString(topCoins)
                val jsonContainer = apiService.getFullPriceList(fSyms = fSyms)
                val coinInfoDtoList = coinMapper.mapJsonContainerToListCoinInfo(jsonContainer)
                val dbModelList = coinInfoDtoList.map { coinMapper.mapDtoToDbModel(it) }
                coinInfoDao.insertPriceList(dbModelList)
            } catch (e: Exception) {
            }
            delay(10000)
        }
    }

    companion object {
        const val NAME = "RefreshDataWorker"

        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
        }
    }
}