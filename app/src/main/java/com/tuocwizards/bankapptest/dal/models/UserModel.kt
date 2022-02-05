package com.tuocwizards.bankapptest.dal.models

data class UserModel(
    val card_number: String,
    val type: String,
    val cardholder_name: String,
    val valid: String,
    val balance: Float,
    val transaction_history: List<HistoryModel>
)
