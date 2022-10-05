package com.arlequins.zoco_1.ui.tabMyProducts.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arlequins.zoco_1.data.ResourceRemote
import com.arlequins.zoco_1.data.StoreRepository
import com.arlequins.zoco_1.model.Store
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.launch

class StoreViewModel : ViewModel() {
    private val storeRepository=  StoreRepository()
    private val storeList: ArrayList<Store> = ArrayList()
    private val _showMsg: MutableLiveData<String?> = MutableLiveData()
    val showMsg: LiveData<String?> = _showMsg

    private val _storeListL: MutableLiveData<ArrayList<Store>> = MutableLiveData()
    val storeListL: LiveData<ArrayList<Store>> = _storeListL

    fun loadStore() {
        viewModelScope.launch {
            val result = storeRepository.searchMyStore()
            result.let { resourceRemote ->
                when(resourceRemote){
                    is ResourceRemote.Success ->{
                        result.data?.documents!!.forEach { doc ->
                            val store = doc.toObject<Store>()
                            if (store !in storeList){
                                store?.let { storeList.add(it) }
                            }
                        }
                        _storeListL.postValue(storeList)
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