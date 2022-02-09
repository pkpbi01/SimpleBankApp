package com.tuocwizards.bankapptest.app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.tuocwizards.bankapptest.R
import com.tuocwizards.bankapptest.app.models.SelecteCardItemModel
import com.tuocwizards.bankapptest.databinding.SelectCardItemBinding
import kotlinx.coroutines.NonDisposableHandle.parent

class SelectCardAdapter: RecyclerView.Adapter<SelectCardAdapter.SelectCardHolder>() {

    private val cardList = ArrayList<SelecteCardItemModel>()
    private var currentCardId: Int = 0

    private lateinit var clickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        clickListener = listener
    }

    class SelectCardHolder(item: View, listener: OnItemClickListener, context: Context): RecyclerView.ViewHolder(item) {
        private val binding = SelectCardItemBinding.bind(item)
        private val context = context

        fun bind(model: SelecteCardItemModel, currentCardId: Int) = with(binding) {
            cardNumberItem.text = model.cardNumber
            if (model.cardId == currentCardId) {
                selectedCard.background = getDrawable(context, R.drawable.blue_shape)
            }
        }
        init {
            item.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectCardHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.select_card_item, parent, false)
        return SelectCardHolder(view, clickListener, parent.context)
    }

    override fun onBindViewHolder(holder: SelectCardHolder, position: Int) {
        holder.bind(cardList[position], currentCardId)
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    fun fillList(list: ArrayList<SelecteCardItemModel>, cardId: Int) {
        cardList.clear()
        cardList.addAll(list)
        currentCardId = cardId
        notifyDataSetChanged()
    }
}