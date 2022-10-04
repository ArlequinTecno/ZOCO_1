package com.arlequins.zoco_1.ui.tabMyProducts.articles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arlequins.zoco_1.data.ArticleRepository
import com.arlequins.zoco_1.data.ResourceRemote
import com.arlequins.zoco_1.data.UserRepository
import com.arlequins.zoco_1.model.Article
import com.arlequins.zoco_1.model.User
import kotlinx.coroutines.launch

class NewArticleViewModel : ViewModel() {

    private val _showMsg: MutableLiveData<String?> = MutableLiveData()
    val showMsg: LiveData<String?> = _showMsg

    private val _createArticleSuccess: MutableLiveData<String?> = MutableLiveData()
    var createArticleSuccess: LiveData<String?> = _createArticleSuccess

    private val articleRepository = ArticleRepository()
    private val userRepository = UserRepository()

    fun validateFields(
        name: String,
        price: String,
        description: String,
        category: String,
        store: String,
        state : String
    ) {
        if (name.isEmpty() ||
            price.isEmpty() ||
            description.isEmpty() ||
            category.isEmpty() ||
            store.isEmpty()
            ){
            _showMsg.postValue("Todos los campos son obligatorios")
        }
        else {
            viewModelScope.launch {
                val article = Article(
                    name = name,
                    price = price,
                    description = description,
                    category = category,
                    store = store,
                    state = state,
                    urlPicture = ""
                )
                val user = User()
                val myUser = userRepository.myUser(user)
                myUser.let { resourceRemoteUser ->
                    when(resourceRemoteUser) {
                        is ResourceRemote.Success -> {
                            article.uname = user.name
                            article.phone = user.phone
                        }
                        is ResourceRemote.Error -> {
                            article.uname = ""
                            article.phone = ""
                        }
                        is ResourceRemote.Loading -> TODO()
                    }
                }

                val result = articleRepository.createArticle(article)
                result.let { resourceRemote ->
                    when (resourceRemote) {
                        is ResourceRemote.Success -> {
                            _createArticleSuccess.postValue(result.data)
                            _showMsg.postValue("EL producto se guardó exitosamente")
                        }
                        is ResourceRemote.Error -> {
                            val msg = result.message
                            _showMsg.postValue(msg)
                        }
                        is ResourceRemote.Loading -> TODO()
                    }
                }
            }
        }
    }
}