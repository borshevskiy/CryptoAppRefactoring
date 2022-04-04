package com.borshevskiy.cryptoapprefactoring

import android.app.Application
import androidx.work.Configuration
import com.borshevskiy.cryptoapprefactoring.data.workers.RefreshDataWorkerFactory
import com.borshevskiy.cryptoapprefactoring.di.components.DaggerAppComponent
import javax.inject.Inject

class CryptoApp: Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: RefreshDataWorkerFactory
    val component by lazy { DaggerAppComponent.factory().create(this) }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder().setWorkerFactory(workerFactory).build()
    }
}