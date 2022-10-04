package com.arlequins.zoco_1.ui.menuDrawer.index

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

class IndexViewModel : ViewModel() {

    private val articleRepository = ArticleRepository()
    private var articleList: ArrayList<Article> = ArrayList()
    private var articleAcademicList: ArrayList<Article> = ArrayList()
    private var articleFoodList: ArrayList<Article> = ArrayList()
    private var articleServiceList: ArrayList<Article> = ArrayList()
    //private var articleDiscountList: ArrayList<Article> = ArrayList()

    private val _showMsg: MutableLiveData<String?> = MutableLiveData()
    val showMsg: LiveData<String?> = _showMsg

    private val _articlesList: MutableLiveData<ArrayList<Article>> = MutableLiveData()
    val articlesList: LiveData<ArrayList<Article>> = _articlesList
    private val _articlesAcademicsList: MutableLiveData<ArrayList<Article>> = MutableLiveData()
    val articlesAcademicsList: LiveData<ArrayList<Article>> = _articlesAcademicsList
    private val _articlesFoodsList: MutableLiveData<ArrayList<Article>> = MutableLiveData()
    val articlesFoodsList: LiveData<ArrayList<Article>> = _articlesFoodsList
    private val _articlesServicesList: MutableLiveData<ArrayList<Article>> = MutableLiveData()
    val articlesServicesList: LiveData<ArrayList<Article>> = _articlesServicesList
    //private val _articlesDiscountsList: MutableLiveData<ArrayList<Article>> = MutableLiveData()
    //val articlesDiscountsList: LiveData<ArrayList<Article>> = _articlesDiscountsList

    fun loadArticles() {
        viewModelScope.launch{
            val result = articleRepository.searchArticle()
            result.let{resourceRemote ->
                when(resourceRemote){
                    is ResourceRemote.Success ->{
                        result.data?.documents!!.forEach { doc ->
                            val article = doc.toObject<Article>()
                            if (article !in articleList) {
                                article?.let { articleList.add(article) }
                                when (article?.category) {
                                    "Académicos" -> article.let { articleAcademicList.add(article) }
                                    "Alimentos" -> article.let { articleFoodList.add(article) }
                                    "Servicios" -> article.let { articleServiceList.add(article) }
                                }
                            }

                        }
                        _articlesList.postValue(articleList)
                        _articlesAcademicsList.postValue(articleAcademicList)
                        _articlesFoodsList.postValue(articleFoodList)
                        _articlesServicesList.postValue(articleServiceList)
                    }
                    is ResourceRemote.Error ->{
                        val msg = result.message
                        _showMsg.postValue(msg)
                    }
                    is ResourceRemote.Loading -> TODO()

                }
            }
        }

    }

}