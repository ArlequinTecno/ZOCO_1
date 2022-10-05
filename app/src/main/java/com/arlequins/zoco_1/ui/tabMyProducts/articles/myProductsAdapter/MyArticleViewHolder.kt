package com.arlequins.zoco_1.ui.tabMyProducts.articles.myProductsAdapter

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.arlequins.zoco_1.R
import com.arlequins.zoco_1.databinding.CardViewMyArticleItemBinding
import com.arlequins.zoco_1.model.Article


class MyArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    private val binding = CardViewMyArticleItemBinding.bind(itemView)

    @SuppressLint("ResourceAsColor")
    fun bind(article: Article){
        with(binding){
            nameArticleTextView.text = article.name
            priceArticleTextView.text = article.price
            descriptionArticleTextView.text = article.description
            stateMyArticleTextView.text = article.state

            if (article.state == "Oculto"){
                stateMyArticleTextView.setTextColor(R.color.warning_color)
            }
            else{
                stateMyArticleTextView.setTextColor(R.color.primary_text)
            }

            /*if (article.urlPicture.isNullOrEmpty()){
                Picasso.get().load(article.urlPicture).into(articleImageView)
            }*/
        }

    }
}
