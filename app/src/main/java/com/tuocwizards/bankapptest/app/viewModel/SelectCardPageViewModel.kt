package com.tuocwizards.bankapptest.app.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuocwizards.bankapptest.app.adapters.SelectCardAdapter
import com.tuocwizards.bankapptest.app.models.SelecteCardItemModel
import com.tuocwizards.bankapptest.bll.DataInteractor
import com.tuocwizards.bankapptest.dal.models.Users
import kotlinx.coroutines.launch

class SelectCardPageViewModel(private val dataInteractor: DataInteractor): ViewModel() {

    val selectCardAdapter = SelectCardAdapter()
    val cardList = ArrayList<SelecteCardItemModel>()
    private lateinit var users: Users

    init {
        viewModelScope.launch {
            users = dataInteractor.getUsersData()
            fillCardslist()
        }
    }

    fun fillCardslist() {
        for (user in users.users) {
            val item = SelecteCardItemModel(
                cardNumber = user.card_number,
                iconSrc = 1
            )
            cardList.add(item)
        }
        selectCardAdapter.fillList(cardList)
    }
}