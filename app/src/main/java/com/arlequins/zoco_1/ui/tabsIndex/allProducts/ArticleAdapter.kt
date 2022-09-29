package com.arlequins.zoco_1.ui.tabsIndex.allProducts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arlequins.zoco_1.R
import com.arlequins.zoco_1.databinding.CardViewArticleItemBinding
import com.arlequins.zoco_1.model.Article
import com.squareup.picasso.Picasso

class AllProductsAdapter(
    private val articleList: ArrayList<Article>,
    private val onItemClicked: (Article) -> Unit
    ) : RecyclerView.Adapter<AllProductsAdapter.ArticleViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_article_item, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articleList[position]
        holder.bind(article)
        holder.itemView.setOnClickListener {onItemClicked(articleList[position])}
    }

    override fun getItemCount(): Int = articleList.size

    fun appendItems(newList: ArrayList<Article>){
        articleList.clear()
        articleList.addAll(newList)
        notifyDataSetChanged()
    }

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val binding = CardViewArticleItemBinding.bind(itemView)

        fun bind(article: Article){
            with(binding){
                nameArticleTextView.text = article.name
                priceArticleTextView.text = article.price
                descriptionArticleTextView.text = article.description

                /*if (article.urlPicture.isNullOrEmpty()){
                    Picasso.get().load(article.urlPicture).into(articleImageView)
                }*/
            }

        }
    }


}