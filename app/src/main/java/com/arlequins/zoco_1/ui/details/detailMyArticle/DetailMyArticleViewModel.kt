package com.arlequins.zoco_1.ui.details.detailMyArticle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arlequins.zoco_1.data.ArticleRepository
import com.arlequins.zoco_1.data.ResourceRemote
import com.arlequins.zoco_1.model.Article
import kotlinx.coroutines.launch

class DetailMyArticleViewModel : ViewModel() {
    private val articleRepository = ArticleRepository()
    private val _showMsg: MutableLiveData<String?> = MutableLiveData()
    val showMsg: LiveData<String?> = _showMsg
    private val _editResult: MutableLiveData<Boolean?> = MutableLiveData()
    val editResult: LiveData<Boolean?> = _editResult
    private val _deleteResult: MutableLiveData<Boolean?> = MutableLiveData()
    val deleteResult: LiveData<Boolean?> = _deleteResult

    fun edit(article: Article) {
        viewModelScope.launch {
            if (article.name!!.isEmpty() || article.price!!.isEmpty()){
                _showMsg.postValue("Todos los campos son obligatorios")
            }
            else{
                val result = articleRepository.editMyArticle(article)
                result.let { resourceRemote ->
                    when(resourceRemote){
                        is ResourceRemote.Success -> {
                            _editResult.postValue(true)
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

    fun deleteArticle(articleID: String?) {
        viewModelScope.launch{
            val result = articleRepository.deleteArticle(articleID)
            result.let { resourceRemote ->
                when(resourceRemote){
                    is ResourceRemote.Success ->{
                        _deleteResult.postValue(true)
                    }
                    is ResourceRemote.Error ->{
                        _showMsg.postValue(result.message)
                    }
                    is ResourceRemote.Loading ->TODO()
                }
            }
        }

    }

}