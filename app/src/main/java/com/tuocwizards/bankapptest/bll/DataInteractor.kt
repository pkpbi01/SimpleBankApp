package com.tuocwizards.bankapptest.bll

import com.tuocwizards.bankapptest.dal.models.CurrenciesModel
import com.tuocwizards.bankapptest.dal.models.Users
import com.tuocwizards.bankapptest.dal.models.ValutesModel
import com.tuocwizards.bankapptest.dal.repository.DataRecipient

class DataInteractor {
    private val dataRecipient = DataRecipient()
    suspend fun getUsersData(): Users {
        return dataRecipient.getUserData()
    }
    suspend fun getCurrencyData(): CurrenciesModel {
        return dataRecipient.getCurrencyData()
    }
}