package com.borshevskiy.cryptoapprefactoring.presentation

import androidx.lifecycle.ViewModel
import com.borshevskiy.cryptoapprefactoring.domain.GetCoinInfoListUseCase
import com.borshevskiy.cryptoapprefactoring.domain.GetCoinInfoUseCase
import com.borshevskiy.cryptoapprefactoring.domain.LoadDataUseCase
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val loadDataUseCase: LoadDataUseCase) : ViewModel() {

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        loadDataUseCase()
    }
}