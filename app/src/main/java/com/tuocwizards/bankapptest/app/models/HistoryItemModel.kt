package com.tuocwizards.bankapptest.app.models

data class HistoryItemModel(
    val iconSrc: Int,
    val title: String,
    val date: String,
    val currencySymbol: String,
    val convertedPrice: String,
    val price: String
)