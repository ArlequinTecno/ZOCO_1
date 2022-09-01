package com.arlequins.zoco_1.ui.tabMyProducts.articles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ArticlesViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is article Fragment"
    }
    val text: LiveData<String> = _text
}