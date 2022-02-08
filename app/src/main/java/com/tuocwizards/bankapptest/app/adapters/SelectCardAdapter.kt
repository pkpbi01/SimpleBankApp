package com.tuocwizards.bankapptest.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tuocwizards.bankapptest.R
import com.tuocwizards.bankapptest.app.models.SelecteCardItemModel
import com.tuocwizards.bankapptest.databinding.SelectCardItemBinding

class SelectCardAdapter: RecyclerView.Adapter<SelectCardAdapter.SelectCardHolder>() {

    private val cardList = ArrayList<SelecteCardItemModel>()

    class SelectCardHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = SelectCardItemBinding.bind(item)

        fun bind(model: SelecteCardItemModel) = with(binding) {
            cardNumberItem.text = model.cardNumber
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectCardHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.select_card_item, parent, false)
        return SelectCardHolder(view)
    }

    override fun onBindViewHolder(holder: SelectCardHolder, position: Int) {
        holder.bind(cardList[position])
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    fun fillList(list: ArrayList<SelecteCardItemModel>) {
        cardList.clear()
        cardList.addAll(list)
        notifyDataSetChanged()
    }
}