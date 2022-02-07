package com.tuocwizards.bankapptest.bll

import com.tuocwizards.bankapptest.dal.models.CurrenciesModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.math.RoundingMode
import java.text.DecimalFormat

class CurrencyConverter {
    private var dataInteractor: DataInteractor = DataInteractor()
    private lateinit var currencies: CurrenciesModel

    init {
        fillCurrencies()
    }

    private fun fillCurrencies(){
//        GlobalScope.launch {
//            currencies = dataInteractor.getCurrencyData()
//        }
    }

    fun convertCurrency(amount: Float, currency: String, currencies: CurrenciesModel): Float {
        val rubAmount = amount * currencies.Valute.USD.Value
        return when (currency) {
            "EUR" -> roundAmount(rubAmount / currencies.Valute.EUR.Value)
            "GBP" -> roundAmount(rubAmount / currencies.Valute.GBP.Value)
            "RUB" -> roundAmount(rubAmount)
            else -> throw IllegalArgumentException()
        }
    }

    fun roundAmount(amount: Float): Float {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        return df.format(amount).toFloat()
    }
}