package com.tuocwizards.bankapptest.app.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tuocwizards.bankapptest.app.viewModel.SelectCardPageViewModel
import com.tuocwizards.bankapptest.bll.DataInteractor

class SelecdCardPageViewModelFactory(
    private val dataInteractor: DataInteractor
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SelectCardPageViewModel(dataInteractor) as T
    }
}