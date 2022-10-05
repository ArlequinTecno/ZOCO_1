package com.arlequins.zoco_1.ui.menuDrawer.category.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.arlequins.zoco_1.databinding.CardViewCategoryItemBinding
import com.arlequins.zoco_1.model.Category

class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    private val binding = CardViewCategoryItemBinding.bind(itemView)

    fun bind(category: Category){
        with(binding){
            nameCategoryItemTextView.text = category.name
            amountItemTextView.text = category.num_articles
        }
    }
}