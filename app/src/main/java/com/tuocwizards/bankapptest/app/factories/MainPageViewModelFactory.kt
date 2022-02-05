package com.tuocwizards.bankapptest.app.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tuocwizards.bankapptest.app.viewModel.MainPageViewModel
import com.tuocwizards.bankapptest.bll.DataInteractor
import com.tuocwizards.bankapptest.dal.repository.DataRecipient

class MainPageViewModelFactory(
    private val dataInteractor: DataInteractor
    ): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainPageViewModel(dataInteractor) as T
    }

}