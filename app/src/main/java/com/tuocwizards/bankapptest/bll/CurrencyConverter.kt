package com.tuocwizards.bankapptest.bll

import com.tuocwizards.bankapptest.dal.models.CurrenciesModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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
            "EUR" -> rubAmount / currencies.Valute.EUR.Value
            "GBP" -> rubAmount / currencies.Valute.GBP.Value
            "RUB" -> rubAmount
            else -> throw IllegalArgumentException()
        }
    }
}