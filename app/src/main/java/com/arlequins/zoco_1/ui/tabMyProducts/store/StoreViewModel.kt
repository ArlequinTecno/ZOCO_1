package com.arlequins.zoco_1.ui.tabMyProducts.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StoreViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is store Fragment"
    }
    val text: LiveData<String> = _text
}