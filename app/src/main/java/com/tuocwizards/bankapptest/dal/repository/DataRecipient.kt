package com.tuocwizards.bankapptest.dal.repository

import android.util.Log
import com.google.gson.Gson
import com.tuocwizards.bankapptest.dal.api.RetrofitInstance
import com.tuocwizards.bankapptest.dal.models.CurrenciesModel
import com.tuocwizards.bankapptest.dal.models.Users

class DataRecipient {

    suspend fun getUserData(): Users {
        try {
            return RetrofitInstance.userApi.getUsers()
        }catch (e: Exception){
            Log.e("HTTP ERROR: 404 -------",
                e.toString()
            )
            val gson = Gson()
            return gson.fromJson(users, Users::class.java)
        }
    }

    suspend fun getCurrencyData(): CurrenciesModel {
        return RetrofitInstance.currencyApi.getCurrency()
    }

    private val users: String = """
        {
          "users": [
            {
              "card_number": "4455 1223 3488 1000",
              "type": "mastercard",
              "cardholder_name": "Anton Ivanov",
              "valid": "12/22",
              "balance": 2617.14,
              "transaction_history": [
                {
                  "title": "Netflix",
                  "icon_url": "https://www.iconfinder.com/icons/143870/download/png/48",
                  "date": "15/12/2019",
                  "amount": "-7.99"
                },
                {
                  "title": "Dropbox",
                  "icon_url": "https://www.iconfinder.com/icons/245986/download/png/48",
                  "date": "14/12/2019",
                  "amount": "-12.50"
                },
                {
                  "title": "Dropbox",
                  "icon_url": "https://www.iconfinder.com/icons/245986/download/png/48",
                  "date": "14/12/2019",
                  "amount": "-12.50"
                }
              ]
            },
            {
              "card_number": "4455 8897 3135 1499",
              "type": "visa",
              "cardholder_name": "Peter Gavrilin",
              "valid": "12/22",
              "balance": 4674.2,
              "transaction_history": [
                {
                  "title": "Netflix",
                  "icon_url": "https://www.iconfinder.com/icons/143870/download/png/48",
                  "date": "14/12/2019",
                  "amount": "-9.85"
                },
                {
                  "title": "Dropbox",
                  "icon_url": "https://www.iconfinder.com/icons/245986/download/png/48",
                  "date": "15/12/2019",
                  "amount": "-15.50"
                },
                {
                  "title": "Facebook",
                  "icon_url": "https://www.iconfinder.com/icons/107175/download/png/48",
                  "date": "15/12/2019",
                  "amount": "-6.99"
                },
                {
                  "title": "Apple",
                  "icon_url": "https://www.iconfinder.com/icons/291727/download/png/48",
                  "date": "15/12/2020",
                  "amount": "-99.85"
                }
              ]
            },
            {
              "card_number": "4455 9921 6789 1999",
              "type": "unionpay",
              "cardholder_name": "Igor Smolov",
              "valid": "12/22",
              "balance": 9317.14,
              "transaction_history": [
                {
                  "title": "Tesla",
                  "icon_url": "https://www.iconfinder.com/icons/3069743/download/png/48",
                  "date": "15/12/2019",
                  "amount": "-4.85"
                },
                {
                  "title": "Dropbox",
                  "icon_url": "https://www.iconfinder.com/icons/245986/download/png/48",
                  "date": "14/12/2019",
                  "amount": "-85.10"
                },
                {
                  "title": "Facebook",
                  "icon_url": "https://www.iconfinder.com/icons/107175/download/png/48",
                  "date": "14/12/2019",
                  "amount": "-65.00"
                }
              ]
            }
          ]
        }
    """.trimIndent()
}
