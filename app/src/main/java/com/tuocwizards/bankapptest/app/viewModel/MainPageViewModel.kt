package com.tuocwizards.bankapptest.app.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuocwizards.bankapptest.app.models.CardModel
import com.tuocwizards.bankapptest.app.models.HistoryItemModel
import com.tuocwizards.bankapptest.bll.DataInteractor
import com.tuocwizards.bankapptest.dal.models.CurrenciesModel
import com.tuocwizards.bankapptest.dal.models.Users
import kotlinx.coroutines.launch

class MainPageViewModel(private val dataInteractor: DataInteractor): ViewModel() {

    var card: CardModel = CardModel(cardNumber = "666", balance = "", convertedBalance = "", date = "", iconSrc = "", userName = "")
    //var card = MutableLiveData<CardModel>(CardModel(cardNumber = "", balance = "", convertedBalance = "", date = "", iconSrc = "", userName = ""))
    fun loadUsers() {
        viewModelScope.launch {
            val users = dataInteractor.getUsersData()
            //val currencies = dataInteractor.getCurrencyData()
            fillCardByDefault(users)
        }
    }

    private fun fillCardByDefault(users: Users) {
        card.balance = users.users[0].balance.toString()
        card.cardNumber = users.users[0].card_number
        card.convertedBalance = users.users[0].balance.toString() //FIx
        card.userName = users.users[0].cardholder_name
        card.date = users.users[0].valid
//        when (users.users[0].type) {
//            "mastercard" -> card.iconSrc =
//        }
    }
}