package com.tuocwizards.bankapptest.app.models

import androidx.databinding.ObservableField

class CardModel {
    var iconSrc = ObservableField("666")
    var cardNumber = ObservableField("____ ____ ____ ____")
    var userName = ObservableField("_____ _____")
    var date = ObservableField("__/__")
    var convertedBalance= ObservableField("____.__")
    var balance = ObservableField("____.__")
}
