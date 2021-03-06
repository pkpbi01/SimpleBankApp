package com.tuocwizards.bankapptest.app.pages

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuocwizards.bankapptest.R
import com.tuocwizards.bankapptest.app.factories.MainPageViewModelFactory
import com.tuocwizards.bankapptest.app.viewModel.MainPageViewModel
import com.tuocwizards.bankapptest.bll.DataInteractor
import com.tuocwizards.bankapptest.databinding.MainPageBinding

class MainPage : Fragment() {

    private lateinit var viewModel: MainPageViewModel


    private var _binding: MainPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataInteractor = DataInteractor()
        val viewModelFactory = MainPageViewModelFactory(dataInteractor)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainPageViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainPageBinding.inflate(inflater, container, false)
        _binding!!.viewmodel = viewModel

        viewModel.cardId = arguments?.getInt("card_id") ?: 0

        binding.apply {
            gbpLayout.setOnClickListener { onGBPCurrencyClicked() }
            eurLayout.setOnClickListener { onEURCurrencyClicked() }
            rubLayout.setOnClickListener { onRUBCurrencyClicked() }
            cardLayout.setOnClickListener { goToSelectCardPage() }

            history.layoutManager = LinearLayoutManager(requireContext())
            history.adapter = viewModel.historyAdapter


        }
        return binding.root
    }

    private fun onGBPCurrencyClicked(){
        binding.apply {
            gbpLayout.background = resources.getDrawable(R.drawable.blue_shape, requireContext().theme)
            eurLayout.background = resources.getDrawable(R.drawable.shape, requireContext().theme)
            rubLayout.background = resources.getDrawable(R.drawable.shape, requireContext().theme)
            gbpSymbol.setTextColor(Color.parseColor("#FFFFFF"))
            gbpText.setTextColor(Color.parseColor("#FFFFFF"))
            eurSymbol.setTextColor(Color.parseColor("#8C9AAE"))
            eurText.setTextColor(Color.parseColor("#8C9AAE"))
            rubSymbol.setTextColor(Color.parseColor("#8C9AAE"))
            rubText.setTextColor(Color.parseColor("#8C9AAE"))
            viewModel.changeCurrency(gbpText.text.toString())
        }
    }

    private fun onEURCurrencyClicked(){
        binding.apply {
            eurLayout.background = resources.getDrawable(R.drawable.blue_shape, requireContext().theme)
            gbpLayout.background = resources.getDrawable(R.drawable.shape, requireContext().theme)
            rubLayout.background = resources.getDrawable(R.drawable.shape, requireContext().theme)
            gbpSymbol.setTextColor(Color.parseColor("#8C9AAE"))
            gbpText.setTextColor(Color.parseColor("#8C9AAE"))
            eurSymbol.setTextColor(Color.parseColor("#FFFFFF"))
            eurText.setTextColor(Color.parseColor("#FFFFFF"))
            rubSymbol.setTextColor(Color.parseColor("#8C9AAE"))
            rubText.setTextColor(Color.parseColor("#8C9AAE"))
            viewModel.changeCurrency(eurText.text.toString())
        }
    }

    private fun onRUBCurrencyClicked(){
        binding.apply {
            rubLayout.background = resources.getDrawable(R.drawable.blue_shape, requireContext().theme)
            gbpLayout.background = resources.getDrawable(R.drawable.shape, requireContext().theme)
            eurLayout.background = resources.getDrawable(R.drawable.shape, requireContext().theme)
            gbpSymbol.setTextColor(Color.parseColor("#8C9AAE"))
            gbpText.setTextColor(Color.parseColor("#8C9AAE"))
            eurSymbol.setTextColor(Color.parseColor("#8C9AAE"))
            eurText.setTextColor(Color.parseColor("#8C9AAE"))
            rubSymbol.setTextColor(Color.parseColor("#FFFFFF"))
            rubText.setTextColor(Color.parseColor("#FFFFFF"))
            viewModel.changeCurrency(rubText.text.toString())
        }
    }

    private fun goToSelectCardPage() {

        val bundle = Bundle()
        bundle.putInt("card_id", viewModel.cardId)

        val selectCardPage = SelectCardPage()
        selectCardPage.arguments = bundle

        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.empty_layout, selectCardPage)
        transaction.disallowAddToBackStack()
        transaction.commit()
    }

}