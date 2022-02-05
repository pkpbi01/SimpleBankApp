package com.tuocwizards.bankapptest.dal.api

import com.tuocwizards.bankapptest.dal.models.CurrenciesModel
import com.tuocwizards.bankapptest.dal.models.Users
import com.tuocwizards.bankapptest.dal.models.ValutesModel
import retrofit2.http.GET

interface BaseApi {
    @GET("v1/users.json")
    suspend fun getUsers(): Users

    @GET("daily_json.js")
    suspend fun getCurrency(): CurrenciesModel
}