package com.tuocwizards.bankapptest.app.viewModel

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuocwizards.bankapptest.app.models.CardModel
import com.tuocwizards.bankapptest.app.models.HistoryItemModel
import com.tuocwizards.bankapptest.bll.CurrencyConverter
import com.tuocwizards.bankapptest.bll.DataInteractor
import com.tuocwizards.bankapptest.dal.models.CurrenciesModel
import com.tuocwizards.bankapptest.dal.models.Users
import kotlinx.coroutines.launch

class MainPageViewModel(private val dataInteractor: DataInteractor): ViewModel() {

    private val currencyConverter = CurrencyConverter()
    var card: CardModel = CardModel()
    var history = ArrayList<HistoryItemModel>()


    private lateinit var currencies: CurrenciesModel
    private lateinit var users: Users
    private val defaultCurrency = "GBP"
    var cardId: Int = 0 //изменяеть при выборе карты

    init {
        viewModelScope.launch {
            users = dataInteractor.getUsersData()
            currencies = dataInteractor.getCurrencyData()
            fillCard()
            fillHistory()
        }
    }



    fun changeCurrency(currencyName: String) {
        val currencySymbol = findCurrencySymbol(currencyName)
        val balance = currencyConverter.roundAmount(users.users[cardId].balance)
        val convertedBalance = currencyConverter.convertCurrency(balance, currencyName, currencies)
        card.convertedBalance.set("$currencySymbol$convertedBalance")
        //добавить изменение на истории
    }

    fun fillHistory() {
        for (item in users.users[cardId].transaction_history) {
            val convertedPrice = currencyConverter.convertCurrency(
                item.amount.toFloat(),
                defaultCurrency,
                currencies).toString()
            val historyItem = HistoryItemModel(
                iconSrc = 1,
                title = item.title,
                date = item.date,
                price = item.amount,
                convertedPrice = convertedPrice
            )
            history.add(historyItem)
        }
    }

    private fun fillCard() {
        val currencySymbol = findCurrencySymbol(defaultCurrency)

        val balance = currencyConverter.roundAmount(users.users[cardId].balance)
        val convertedBalance = currencyConverter.convertCurrency(balance, defaultCurrency, currencies)

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