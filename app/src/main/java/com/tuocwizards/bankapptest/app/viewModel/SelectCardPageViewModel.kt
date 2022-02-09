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
    var currentCardId: Int = 0
    private lateinit var users: Users

    init {
        viewModelScope.launch {
            users = dataInteractor.getUsersData()
            fillCardslist()
        }
    }

    fun fillCardslist() {
        var userId = 0
        for (user in users.users) {
            val item = SelecteCardItemModel(
                cardNumber = user.card_number,
                cardId = userId,
                iconSrc = 1
            )
            cardList.add(item)
            userId++
        }
        selectCardAdapter.fillList(cardList, currentCardId)
    }
}