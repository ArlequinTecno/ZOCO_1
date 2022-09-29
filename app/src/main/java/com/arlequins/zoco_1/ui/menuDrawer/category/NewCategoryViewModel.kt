package com.arlequins.zoco_1.ui.menuDrawer.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arlequins.zoco_1.data.CategoryRepository
import com.arlequins.zoco_1.data.ResourceRemote
import com.arlequins.zoco_1.model.Category
import kotlinx.coroutines.launch


class NewCategoryViewModel : ViewModel() {

    private val _showMsg: MutableLiveData<String?> = MutableLiveData()
    var showMsg: LiveData<String?> = _showMsg

    private val _createCategorySuccess: MutableLiveData<String?> = MutableLiveData()
    var createCategorySuccess: LiveData<String?> = _createCategorySuccess

    private val categoryRepository = CategoryRepository()

    fun validateFields(
        name: String,
        description: String
    ) {
        if (name.isEmpty() || description.isEmpty()){
            _showMsg.postValue("Todos los campos son obligatorios")
        }
        else{
            viewModelScope.launch{
                val category = Category(
                    name = name,
                    description = description
                )
                val result = categoryRepository.createCategory(category)
                result.let { resourceRemote ->
                    when(resourceRemote){
                        is ResourceRemote.Success ->{
                            _createCategorySuccess.postValue(result.data)
                            _showMsg.postValue("La categoría se agregó con exito")
                        }
                        is ResourceRemote.Error -> {
                            val msg = result.message
                            _showMsg.postValue(msg)
                        }
                        else -> {
                            //Don´t use
                        }
                    }

                }
            }
        }
    }
    // TODO: Implement the ViewModel
}