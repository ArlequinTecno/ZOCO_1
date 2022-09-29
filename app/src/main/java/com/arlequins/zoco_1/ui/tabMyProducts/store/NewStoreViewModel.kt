package com.arlequins.zoco_1.ui.tabMyProducts.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arlequins.zoco_1.data.ResourceRemote
import com.arlequins.zoco_1.data.StoreRepository
import com.arlequins.zoco_1.model.Store
import kotlinx.coroutines.launch

class NewStoreViewModel : ViewModel() {

    private val _showMsg: MutableLiveData<String?> = MutableLiveData()
    val showMsg: LiveData<String?> = _showMsg

    private val _createStoreSuccess: MutableLiveData<String?> = MutableLiveData()
    var createStoreSuccess: LiveData<String?> = _createStoreSuccess

    private val storeRepository = StoreRepository()

    fun validateFields(name: String,
                       description: String,
                       location: String,
                       state : String) {
        if (name.isEmpty() ||
            description.isEmpty() ||
            location.isEmpty() ||
            state.isEmpty()
        ){
             _showMsg.value = "Todos los campos son obligatorios"
        }
        else{
            viewModelScope.launch{
                val store = Store(
                    name = name,
                    description = description,
                    location = location,
                    state = state
                )
                val result = storeRepository.createStore(store)
                result.let {resourceRemote ->
                    when(resourceRemote){
                        is ResourceRemote.Success -> {
                            _createStoreSuccess.postValue(result.data)
                            _showMsg.postValue("Se creó su negocio")
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

}