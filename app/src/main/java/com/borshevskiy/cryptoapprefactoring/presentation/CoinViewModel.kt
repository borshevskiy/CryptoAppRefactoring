package com.borshevskiy.cryptoapprefactoring.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.borshevskiy.cryptoapprefactoring.data.repository.CoinRepositoryImpl
import com.borshevskiy.cryptoapprefactoring.domain.GetCoinInfoListUseCase
import com.borshevskiy.cryptoapprefactoring.domain.GetCoinInfoUseCase
import com.borshevskiy.cryptoapprefactoring.domain.LoadDataUseCase
import kotlinx.coroutines.launch

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)

    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        loadDataUseCase()
    }
}