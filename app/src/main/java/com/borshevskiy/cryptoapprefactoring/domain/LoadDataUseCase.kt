package com.borshevskiy.cryptoapprefactoring.domain

class LoadDataUseCase(private val repository: CoinRepository) {

    operator fun invoke() = repository.loadData()
}