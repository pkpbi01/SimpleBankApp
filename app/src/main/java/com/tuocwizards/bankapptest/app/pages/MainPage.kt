package com.tuocwizards.bankapptest.app.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBindings
import com.tuocwizards.bankapptest.R
import com.tuocwizards.bankapptest.app.factories.MainPageViewModelFactory
import com.tuocwizards.bankapptest.app.viewModel.MainPageViewModel
import com.tuocwizards.bankapptest.bll.DataInteractor
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
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainPageLayoutBinding.inflate(inflater, container, false)
        _binding!!.viewmodel = viewModel

        val gbpLayout = ViewBindings.findChildViewById<ViewGroup>(binding.root, R.id.gbp_layout)
        val eurLayout = ViewBindings.findChildViewById<ViewGroup>(binding.root, R.id.eur_layout)
        val rubLayout = ViewBindings.findChildViewById<ViewGroup>(binding.root, R.id.rub_layout)

        gbpLayout!!.setOnClickListener { onValuteClicked(gbpLayout, eurLayout!!, rubLayout!!) }
        eurLayout!!.setOnClickListener { onValuteClicked(eurLayout, gbpLayout, rubLayout!!) }
        rubLayout!!.setOnClickListener { onValuteClicked(rubLayout, eurLayout, gbpLayout) }
        return binding.root
    }

    fun onValuteClicked(mainView: ViewGroup, difView1: ViewGroup, defView2: ViewGroup){
        mainView.background = resources.getDrawable(R.drawable.blue_shape, requireContext().theme)
        difView1.background = resources.getDrawable(R.drawable.shape, requireContext().theme)
        defView2.background = resources.getDrawable(R.drawable.shape, requireContext().theme)
    }




}