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

    init {
        viewModelScope.launch {
            users = dataInteractor.getUsersData()
            currencies = dataInteractor.getCurrencyData()
            fillCardByDefault()
        }
    }




    private fun fillCardByDefault() {
        val convertedBalance = currencyConverter.convertCurrency(users.users[1].balance, "GBP", currencies)
        card.balance.set(users.users[1].balance.toString())
        card.cardNumber.set(users.users[1].card_number)
        card.convertedBalance.set(convertedBalance.toString())
        card.userName.set(users.users[1].cardholder_name)
        card.date.set(users.users[1].valid)
//        when (users.users[0].type) {
//            "mastercard" -> card.iconSrc.set("@drawable/mastercard_icon")
//        }
    }
}