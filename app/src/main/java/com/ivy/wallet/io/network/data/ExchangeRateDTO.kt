package com.ivy.wallet.io.network.data

data class ExchangeRateDTO(
    val baseCurrency: String,
    val currency: String,
    val rate: Double,
) {
    fun toEntity(): ExchangeRateDTO = ExchangeRateDTO(
        baseCurrency = baseCurrency,
        currency = currency,
        rate = rate
    )
}