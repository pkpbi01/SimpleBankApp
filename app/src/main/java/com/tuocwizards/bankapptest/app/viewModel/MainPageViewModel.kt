package com.tuocwizards.bankapptest.app.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuocwizards.bankapptest.app.adapters.HistoryAdapter
import com.tuocwizards.bankapptest.app.models.CardModel
import com.tuocwizards.bankapptest.app.models.HistoryItemModel
import com.tuocwizards.bankapptest.bll.CurrencyConverter
import com.tuocwizards.bankapptest.bll.DataInteractor
import com.tuocwizards.bankapptest.dal.models.CurrenciesModel
import com.tuocwizards.bankapptest.dal.models.Users
import kotlinx.coroutines.launch

class MainPageViewModel(private val dataInteractor: DataInteractor): ViewModel() {

    var cardId: Int = 0 //изменяеть при выборе карты
    var card: CardModel = CardModel()
    var history = ArrayList<HistoryItemModel>()
    val historyAdapter = HistoryAdapter()

    private val currencyConverter = CurrencyConverter()
    private lateinit var currencies: CurrenciesModel
    private lateinit var users: Users
    private val defaultCurrency = "GBP"

    init {
        viewModelScope.launch {
            users = dataInteractor.getUsersData()
            currencies = dataInteractor.getCurrencyData()
            fillCard()
            fillHistory(defaultCurrency)
        }
    }

    fun changeCurrency(currencyName: String) {
        val currencySymbol = findCurrencySymbol(currencyName)
        val convertedBalance = currencyConverter.convertCurrency(users.users[cardId].balance, currencyName, currencies)
        card.convertedBalance.set("$currencySymbol$convertedBalance")
        fillHistory(currencyName)
    }

    private fun fillHistory(currencyName: String) {
        history.clear()
        val currencySymbol = findCurrencySymbol(defaultCurrency)
        for (item in users.users[cardId].transaction_history) {
            val price = item.amount.toFloat() * -1
            val convertedPrice = currencyConverter.convertCurrency(
                price,
                currencyName,
                currencies)

            val historyItem = HistoryItemModel(
                iconSrc = 5,
                title = item.title,
                date = item.date,
                price = "$ $price",
                currencySymbol = "- $currencySymbol ",
                convertedPrice = convertedPrice
            )
            history.add(historyItem)
        }
        historyAdapter.fillList(history)
    }

    private fun fillCard() {
        val currencySymbol = findCurrencySymbol(defaultCurrency)

        val balance = currencyConverter.roundAmount(users.users[cardId].balance)
        val convertedBalance = currencyConverter.convertCurrency(users.users[cardId].balance, defaultCurrency, currencies)

        card.balance.set("$$balance")
        card.cardNumber.set(users.users[cardId].card_number)
        card.convertedBalance.set("$currencySymbol$convertedBalance")
        card.userName.set(users.users[cardId].cardholder_name)
        card.date.set(users.users[cardId].valid)
//        when (users.users[0].type) {
//            "mastercard" -> card.iconSrc.set("@drawable/mastercard_icon") узнать как менять картинки
//        }
    }

    private fun findCurrencySymbol(currencyName: String): String {
        return when (currencyName) {
            "EUR" -> "€"
            "GBP" -> "£"
            "RUB" -> "₽"
            else -> throw IllegalArgumentException()
        }
    }
}