package com.tuocwizards.bankapptest.app.pages

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tuocwizards.bankapptest.R
import com.tuocwizards.bankapptest.app.factories.MainPageViewModelFactory
import com.tuocwizards.bankapptest.app.viewModel.MainPageViewModel
import com.tuocwizards.bankapptest.bll.DataInteractor
import com.tuocwizards.bankapptest.dal.repository.DataRecipient
import com.tuocwizards.bankapptest.databinding.MainPageLayoutBinding

class MainPage : Fragment() {

    private lateinit var viewModel: MainPageViewModel
    private var _binding: MainPageLayoutBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataInteractor = DataInteractor()
        val viewModelFactore = MainPageViewModelFactory(dataInteractor)


        viewModel = ViewModelProvider(this, viewModelFactore).get(MainPageViewModel::class.java)
        viewModel.loadUsers()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainPageLayoutBinding.inflate(inflater, container, false)
        _binding!!.viewmodel = viewModel
        return binding.root
    }

}