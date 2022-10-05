package com.arlequins.zoco_1.ui.tabMyProducts.store.sotreAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arlequins.zoco_1.R
import com.arlequins.zoco_1.model.Store

class StoreAdapter(
    private val storeList: ArrayList<Store>,
    private val onItemClicked: (Store) -> Unit
    ): RecyclerView.Adapter<StoreViewHolder>() {
    override fun getItemCount(): Int = storeList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
       val view = LayoutInflater.from(parent.context)
           .inflate(R.layout.card_view_store_item, parent, false)
        return StoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        val store = storeList[position]
        holder.bind(store)
        holder.itemView.setOnClickListener {onItemClicked(store)}
    }

    fun appendItems(itemList: ArrayList<Store>){
        storeList.clear()
        storeList.addAll(itemList)
        notifyDataSetChanged()
    }
}

