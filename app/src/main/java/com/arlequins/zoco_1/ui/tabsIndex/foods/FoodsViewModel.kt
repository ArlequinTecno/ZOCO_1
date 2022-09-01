package com.arlequins.zoco_1.ui.tabsIndex.foods

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FoodsViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Foods Fragment"
    }
    val text: LiveData<String> = _text
}