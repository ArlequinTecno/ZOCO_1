package com.arlequins.zoco_1.ui.tabsIndex.discount

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DiscountViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Discount Fragment"
    }
    val text: LiveData<String> = _text
}