package com.tuocwizards.bankapptest.dal.repository

import com.tuocwizards.bankapptest.dal.api.RetrofitInstance
import com.tuocwizards.bankapptest.dal.models.CurrenciesModel
import com.tuocwizards.bankapptest.dal.models.Users
import com.tuocwizards.bankapptest.dal.models.ValutesModel

class DataRecipient {

    suspend fun getUserData(): Users {
        return RetrofitInstance.userApi.getUsers()
    }

    suspend fun getCurrencyData(): CurrenciesModel {
        return RetrofitInstance.currencyApi.getCurrency()
    }
}
