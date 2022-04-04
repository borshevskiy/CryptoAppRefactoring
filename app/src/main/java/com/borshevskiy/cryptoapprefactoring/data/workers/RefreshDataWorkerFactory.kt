package com.borshevskiy.cryptoapprefactoring.data.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.borshevskiy.cryptoapprefactoring.data.database.CoinInfoDao
import com.borshevskiy.cryptoapprefactoring.data.mapper.CoinMapper
import com.borshevskiy.cryptoapprefactoring.data.network.ApiService
import javax.inject.Inject

class RefreshDataWorkerFactory @Inject constructor(
    private val coinInfoDao: CoinInfoDao,
    private val coinMapper: CoinMapper,
    private val apiService: ApiService): WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker {
        return RefreshDataWorker(coinInfoDao,coinMapper,apiService,appContext,workerParameters)
    }
}