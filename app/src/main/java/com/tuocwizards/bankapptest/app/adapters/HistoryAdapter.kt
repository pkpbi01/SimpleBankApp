package com.tuocwizards.bankapptest.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tuocwizards.bankapptest.R
import com.tuocwizards.bankapptest.app.models.HistoryItemModel
import com.tuocwizards.bankapptest.databinding.HistoryItemViewBinding

class HistoryAdapter: RecyclerView.Adapter<HistoryAdapter.HistoryHolder>() {

    private val historyList = ArrayList<HistoryItemModel>()

    class HistoryHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = HistoryItemViewBinding.bind(item)

        fun bind(model: HistoryItemModel) = with(binding) {
            //icon.setImageResource(model.iconSrc)
            title.text = model.title
            date.text = model.date
            currencySymbol.text = model.currencySymbol
            convertedPrice.text = model.convertedPrice
            price.text = model.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_item_view, parent, false)
        return HistoryHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        holder.bind(historyList[position])
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    fun fillList(list: ArrayList<HistoryItemModel>) {
        historyList.clear()
        historyList.addAll(list)
        notifyDataSetChanged()
    }
}