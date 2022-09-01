package com.arlequins.zoco_1.ui.tabsIndex.services

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ServicesViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is services Fragment"
    }
    val text: LiveData<String> = _text
}