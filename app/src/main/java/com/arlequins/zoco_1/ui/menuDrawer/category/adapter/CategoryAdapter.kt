package com.arlequins.zoco_1.ui.menuDrawer.category.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arlequins.zoco_1.R
import com.arlequins.zoco_1.model.Category

class CategoryAdapter(
    private val categoryList: ArrayList<Category>,
    private val onItemClicked: (Category) -> Unit
    ): RecyclerView.Adapter<CategoryViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_category_item, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoryList[position]
        holder.bind(category)
        holder.itemView.setOnClickListener {onItemClicked(category)}
    }

    override fun getItemCount(): Int = categoryList.size

    fun appendItems(newList:ArrayList<Category>){
        categoryList.clear()
        categoryList.addAll(newList)
        notifyDataSetChanged()
    }
}