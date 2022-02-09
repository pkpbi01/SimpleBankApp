package com.tuocwizards.bankapptest.app.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuocwizards.bankapptest.R
import com.tuocwizards.bankapptest.app.adapters.SelectCardAdapter
import com.tuocwizards.bankapptest.app.factories.SelecdCardPageViewModelFactory
import com.tuocwizards.bankapptest.app.viewModel.SelectCardPageViewModel
import com.tuocwizards.bankapptest.bll.DataInteractor
import com.tuocwizards.bankapptest.databinding.SelectCardPageBinding

class SelectCardPage : Fragment() {

    private lateinit var viewModel: SelectCardPageViewModel

    private var _binding: SelectCardPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataInteractor = DataInteractor()
        val viewModelFactory = SelecdCardPageViewModelFactory(dataInteractor)
        viewModel = ViewModelProvider(this, viewModelFactory)[SelectCardPageViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SelectCardPageBinding.inflate(inflater, container, false)

        binding.apply {
            backButton.setOnClickListener { goToMainPage(0) }

            cardList.layoutManager = LinearLayoutManager(requireContext())
            cardList.adapter = viewModel.selectCardAdapter
            viewModel.selectCardAdapter.setOnItemClickListener(object : SelectCardAdapter.OnItemClickListener{
                override fun onItemClick(position: Int) {
                    goToMainPage(position)
                }
            })
        }
        return binding.root
    }

    private fun goToMainPage(id: Int){
        val bundle = Bundle()
        bundle.putInt("card_id", id)

        val mainPage = MainPage()
        mainPage.arguments = bundle

        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.empty_layout, mainPage)
        transaction.disallowAddToBackStack()
        transaction.commit()
    }
}