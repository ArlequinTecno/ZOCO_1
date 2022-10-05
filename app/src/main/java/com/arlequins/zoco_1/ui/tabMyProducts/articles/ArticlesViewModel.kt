package com.arlequins.zoco_1.ui.tabMyProducts.articles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arlequins.zoco_1.data.ArticleRepository
import com.arlequins.zoco_1.data.ResourceRemote
import com.arlequins.zoco_1.model.Article
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.launch

class ArticlesViewModel : ViewModel() {
    private val myArticleRepository = ArticleRepository()
    private var myArticleList: ArrayList<Article> = ArrayList()
    private val _myArticlesList: MutableLiveData<ArrayList<Article>>
    = MutableLiveData()
    val myArticlesList: LiveData<ArrayList<Article>> = _myArticlesList
    private val _showMsg: MutableLiveData<String?> = MutableLiveData()
    val showMsg: LiveData<String?> = _showMsg

    fun loadMyArticles() {
        viewModelScope.launch{
            val result = myArticleRepository.searchMyArticle()
            result.let { resourceRemote ->
                when(resourceRemote){
                    is ResourceRemote.Success -> {
                        result.data?.documents!!.forEach { doc ->
                            val article = doc.toObject<Article>()
                            if (article !in myArticleList){
                                article?.let{myArticleList.add(article)}
                            }
                        }
                        _myArticlesList.postValue(myArticleList)
                    }
                    is ResourceRemote.Error ->{
                        _showMsg.postValue(result.message)
                    }
                    is ResourceRemote.Loading -> TODO()
                }

            }
        }
    }


}