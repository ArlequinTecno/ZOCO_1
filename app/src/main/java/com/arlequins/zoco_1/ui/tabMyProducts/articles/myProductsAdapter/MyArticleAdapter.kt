package com.arlequins.zoco_1.ui.tabMyProducts.articles.myProductsAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arlequins.zoco_1.R
import com.arlequins.zoco_1.model.Article

class MyArticleAdapter(
    private val articleList: ArrayList<Article> ,
    private val onItemClicked: (Article) -> Unit
) : RecyclerView.Adapter<MyArticleViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MyArticleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_my_article_item, parent, false)
        return MyArticleViewHolder(view)
    }
    fun appendItems(newList: ArrayList<Article>){
        articleList.clear()
        articleList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = articleList.size
    override fun onBindViewHolder(holder: MyArticleViewHolder, position: Int) {
        val article = articleList[position]
        holder.bind(article)
        holder.itemView.setOnClickListener {onItemClicked(articleList[position])}
    }


}