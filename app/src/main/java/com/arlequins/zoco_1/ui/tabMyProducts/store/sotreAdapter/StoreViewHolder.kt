package com.arlequins.zoco_1.ui.tabMyProducts.store.sotreAdapter

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.arlequins.zoco_1.R
import com.arlequins.zoco_1.databinding.CardViewStoreItemBinding
import com.arlequins.zoco_1.model.Store

class StoreViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    private val binding =  CardViewStoreItemBinding.bind(itemView)


    fun bind(store: Store){
        with(binding){
            nameStoreItemTextView.text = store.name
            locateStoreItemTextView.text = store.location
            descriptionStoreItemTextView.text = store.description
            stateStoreItemTextView.text = store.state

        }
    }

}