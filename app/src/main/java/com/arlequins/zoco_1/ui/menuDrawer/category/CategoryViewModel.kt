package com.arlequins.zoco_1.ui.menuDrawer.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arlequins.zoco_1.data.CategoryRepository
import com.arlequins.zoco_1.data.ResourceRemote
import com.arlequins.zoco_1.model.Category
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {
    private val categoryRepository = CategoryRepository()
    private val categoryList: ArrayList<Category> = ArrayList()
    private val _showMsg: MutableLiveData<String?> = MutableLiveData()
    val showMsg: LiveData<String?> = _showMsg
    private val _categoryResult: MutableLiveData<ArrayList<Category>>
    = MutableLiveData()
    val categoryResult: LiveData<ArrayList<Category>> = _categoryResult


    fun loadCategory() {
        viewModelScope.launch{
            val result = categoryRepository.searchCategory()
            result.let { resourceRemote ->
                when(resourceRemote){
                    is ResourceRemote.Success -> {
                        result.data?.documents!!.forEach { doc ->
                            val category = doc.toObject<Category>()
                            if (category !in categoryList){
                                category?.let { categoryList.add(it) }
                            }
                        }
                        _categoryResult.postValue(categoryList)
                    }
                    is ResourceRemote.Error -> {
                        _showMsg.postValue(result.message)
                    }
                    is ResourceRemote.Loading -> TODO()
                }

            }
        }
    }

}