package com.arlequins.zoco_1.data


import android.util.Log
import com.arlequins.zoco_1.model.Article
import com.arlequins.zoco_1.model.Category
import com.google.firebase.firestore.ktx.toObject

class SearchRepository {
    private val articleRepository = ArticleRepository()
    private val categoryRepository = CategoryRepository()

    suspend fun search(newText: String, context : String): String {

        when(context){
            "index" ->{
                val result = articleRepository.searchArticle()

                result.data?.documents!!.forEach{ doc ->
                    val article = doc.toObject<Article>()
                    val name = article?.name.toString()
                    article?.name?.let { Log.d("Index", it) }
                    if (inString(name, newText))
                        return "Nombre: $name " +
                                "\nPrecio: ${article?.price}" +
                                "\nDescripción: ${article?.description}"

                }
            }
            "category" -> {
                val result = categoryRepository.searchCategory()
                result.data?.documents!!.forEach{ doc ->
                    val category = doc.toObject<Category>()
                    category?.name?.let {Log.d("Category", it)}
                    return category?.name!!
                }
            }
            "my_products" ->{
                val result = articleRepository.searchMyArticle()
                result.data?.documents!!.forEach { doc ->
                    val article = doc.toObject<Article>()
                    article?.name?.let { Log.d("My_Products", it) }
                    return article?.name!!
                }
            }
            /*
            "my_articles" ->{
                val result = articleRepository.searchMyArticle()
                result.data?.documents!!.forEach { doc ->
                    val article = doc.toObject<Article>()
                    article?.name?.let { Log.d("My_Article", it) }
                }
             }
             "my_stors" ->{
                val result = storeRepository.searchMyStore()
                result.data?.documents!!.forEach { doc ->
                    val store = doc.toObject<Store>()
                    store?.name?.let { Log.d("My_Store", it) }
                }
             }
             "stors" ->{
                val result = storeRepository.searchStore()
                result.data?.documents!!.forEach { doc ->
                    val store = doc.toObject<Store>()
                    store?.name?.let { Log.d("Store", it) }
                }
             }
            */
        }
        return ""
    }


    private fun inString(fist: String, second: String): Boolean{
        val len1 : Int = fist.length
        val len2: Int = second.length
        if (len1>len2){
            for (i in 0..len1 - len2) {
                if (
                    fist.substring(startIndex = i, endIndex = i + len2).contains(second))
                    return true
            }
        }
        else{
            for (i in 0..len2 - len1) {
                if (
                    second.substring(startIndex = i, endIndex = i + len1).contains(fist))
                    return true
            }
        }
        return false
    }

}