package com.tuocwizards.bankapptest.dal.api

import com.tuocwizards.bankapptest.dal.uril.Constants.Companion.BASE_CURRENCY_URL
import com.tuocwizards.bankapptest.dal.uril.Constants.Companion.BASE_USER_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private  val userRetrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_USER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val userApi: BaseApi by lazy {
        userRetrofit.create(BaseApi::class.java)
    }

    private  val currencyRetrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_CURRENCY_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val currencyApi: BaseApi by lazy {
        currencyRetrofit.create(BaseApi::class.java)
    }
}