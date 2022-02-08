package com.tuocwizards.bankapptest.bll

import com.tuocwizards.bankapptest.dal.models.CurrenciesModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.math.RoundingMode
import java.text.DecimalFormat

class CurrencyConverter {

    fun convertCurrency(amount: Float, currency: String, currencies: CurrenciesModel): String {
        val rubAmount = amount * currencies.Valute.USD.Value
        return when (currency) {
            "EUR" -> roundAmount(rubAmount / currencies.Valute.EUR.Value)
            "GBP" -> roundAmount(rubAmount / currencies.Valute.GBP.Value)
            "RUB" -> roundAmount(rubAmount)
            else -> throw IllegalArgumentException()
        }
    }

    fun roundAmount(amount: Float): String {
//        val df = DecimalFormat("#.##")
//        df.roundingMode = RoundingMode.HALF_EVEN

        return amount.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN).toString()
    }
}