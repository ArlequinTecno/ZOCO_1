package com.arlequins.zoco_1.ui.tabsIndex.allProducts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arlequins.zoco_1.data.ArticleRepository
import com.arlequins.zoco_1.data.ResourceRemote
import com.arlequins.zoco_1.model.Article
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.launch

class AllProductsViewModel : ViewModel() {

    private val articleRepository = ArticleRepository()
    private var articleList: ArrayList<Article> = ArrayList()

    private val _showMsg: MutableLiveData<String?> = MutableLiveData()
    val showMsg: LiveData<String?> = _showMsg

    private val _articlesList: MutableLiveData<ArrayList<Article>> = MutableLiveData()
    val articlesList: LiveData<ArrayList<Article>> = _articlesList

    fun loadArticles() {
        viewModelScope.launch{
            val result = articleRepository.searchArticle()
            result.let{resourceRemote ->
                when(resourceRemote){
                    is ResourceRemote.Success ->{
                        result.data?.documents!!.forEach { doc ->
                            val article = doc.toObject<Article>()
                            article?.let{articleList.add(article)}

                        }
                        _articlesList.postValue(articleList)
                    }
                    is ResourceRemote.Error ->{
                        val msg = result.message
                        _showMsg.postValue(msg)

                    }
                    else ->{
                        //don´t use
                    }
                }

            }
        }

    }


}