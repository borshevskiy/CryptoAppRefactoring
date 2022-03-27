package com.borshevskiy.cryptoapprefactoring.data.mapper

import com.borshevskiy.cryptoapprefactoring.data.database.CoinInfoDbModel
import com.borshevskiy.cryptoapprefactoring.data.network.model.CoinInfoDto
import com.borshevskiy.cryptoapprefactoring.data.network.model.CoinInfoJsonContainerDto
import com.borshevskiy.cryptoapprefactoring.data.network.model.CoinNamesListDto
import com.borshevskiy.cryptoapprefactoring.domain.CoinInfo
import com.google.gson.Gson

class CoinMapper {

    fun mapDtoToDbModel(dto: CoinInfoDto): CoinInfoDbModel = CoinInfoDbModel(
        dto.fromSymbol,
        dto.toSymbol,
        dto.price,
        dto.lastUpdate,
        dto.highDay,
        dto.lowDay,
        dto.lastMarket,
        dto.imageUrl
    )

    fun mapJsonContainerToListCoinInfo(jsonContainer: CoinInfoJsonContainerDto): List<CoinInfoDto> {
        val result = mutableListOf<CoinInfoDto>()
        val jsonObject = jsonContainer.json ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinInfoDto::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    fun mapNamesListToString(namesListDto: CoinNamesListDto): String {
        return namesListDto.names?.map { it.coinName?.name }?.joinToString(",") ?: ""
    }

    fun mapDbModelToEntity(dbModel: CoinInfoDbModel): CoinInfo = CoinInfo(
        dbModel.fromSymbol,
        dbModel.toSymbol,
        dbModel.price,
        dbModel.lastUpdate,
        dbModel.highDay,
        dbModel.lowDay,
        dbModel.lastMarket,
        dbModel.imageUrl
    )

}