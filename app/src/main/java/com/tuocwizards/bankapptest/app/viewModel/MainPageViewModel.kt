package com.tuocwizards.bankapptest.app.viewModel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
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

    val currencyConverter = CurrencyConverter()
    var card: CardModel = CardModel()
    lateinit var currencies: CurrenciesModel
    lateinit var users: Users
    var cardId: Int = 0

    init {
        viewModelScope.launch {
            users = dataInteractor.getUsersData()
            currencies = dataInteractor.getCurrencyData()
            fillCard("GBP")
        }
    }

    private fun fillCard(currencyName: String) {
        val currencySymbol = findCurrencySymbol(currencyName)
        val balance = currencyConverter.roundAmount(users.users[cardId].balance)
        val convertedBalance = currencyConverter.convertCurrency(balance, currencyName, currencies)
        card.balance.set("$$balance")
        card.cardNumber.set(users.users[cardId].card_number)
        card.convertedBalance.set("$currencySymbol$convertedBalance")
        card.userName.set(users.users[cardId].cardholder_name)
        card.date.set(users.users[cardId].valid)
//        when (users.users[0].type) {
//            "mastercard" -> card.iconSrc.set("@drawable/mastercard_icon")
//        }
    }

    fun ChangeCurrency(currencyName: String) {
        val currencySymbol = findCurrencySymbol(currencyName)
        val balance = currencyConverter.roundAmount(users.users[cardId].balance)
        val convertedBalance = currencyConverter.convertCurrency(balance, currencyName, currencies)
        card.convertedBalance.set("$currencySymbol$convertedBalance")
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