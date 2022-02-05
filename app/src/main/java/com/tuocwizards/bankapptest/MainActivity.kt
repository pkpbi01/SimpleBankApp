package com.tuocwizards.bankapptest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.tuocwizards.bankapptest.app.pages.MainPage
import com.tuocwizards.bankapptest.app.viewModel.MainPageViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainPageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.empty_layout, MainPage()).commit()
    }
}