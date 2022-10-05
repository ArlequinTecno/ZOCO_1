package com.arlequins.zoco_1.ui.details.detailStore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arlequins.zoco_1.data.ResourceRemote
import com.arlequins.zoco_1.data.StoreRepository
import com.arlequins.zoco_1.model.Store
import kotlinx.coroutines.launch

class DetailStoreViewModel : ViewModel() {
    private val storeRepository = StoreRepository()
    private val _showMsg: MutableLiveData<String?> = MutableLiveData()
    val showMsg: LiveData<String?> = _showMsg
    private val _editResult: MutableLiveData<Boolean?> = MutableLiveData()
    val editResult: LiveData<Boolean?> = _editResult
    private val _deleteResult: MutableLiveData<Boolean?> = MutableLiveData()
    val deleteResult: LiveData<Boolean?> = _deleteResult

    fun edit(store: Store) {
        viewModelScope.launch {
            if(store.name!!.isEmpty() || store.location!!.isEmpty() || store.description!!.isEmpty()) {
                _showMsg.postValue("Todos los campos son obligatorios")
            }
            else {
                val result = storeRepository.editMyStore(store)
                result.let { resourceRemote ->
                    when(resourceRemote){
                        is ResourceRemote.Success -> {
                            _editResult.postValue(true)
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

    fun deleteStore(storeID: String?) {
        viewModelScope.launch{
            val result = storeRepository.deleteStore(storeID)
            result.let { resourceRemote ->
                when (resourceRemote){
                    is ResourceRemote.Success -> {
                        _deleteResult.postValue(true)
                    }
                    is ResourceRemote.Error ->{
                        _showMsg.postValue(result.message)
                    }
                    is ResourceRemote.Loading -> TODO()
                }

            }
        }
    }
    // TODO: Implement the ViewModel
}